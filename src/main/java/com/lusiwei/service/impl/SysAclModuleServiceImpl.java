package com.lusiwei.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lusiwei.dao.SysAclModuleMapper;
import com.lusiwei.dto.SysAclModuleDto;
import com.lusiwei.dto.SysAclModuleTreeDto;
import com.lusiwei.exception.ParamException;
import com.lusiwei.pojo.SysAclModule;
import com.lusiwei.service.SysAclModuleService;
import com.lusiwei.util.BeanValidator;
import com.lusiwei.util.IPUtils;
import com.lusiwei.util.LevelUtil;
import com.lusiwei.util.ThreadLocalCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service
public class SysAclModuleServiceImpl implements SysAclModuleService {
    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;
    @Override
    public void insert(SysAclModuleDto sysAclModuleDto) {
        //校验数据
        BeanValidator.check(sysAclModuleDto);
        //判断同一个父部门下部门名称不能重复
        checkAclModuleName(sysAclModuleDto.getName(),sysAclModuleDto.getParentId(),sysAclModuleDto.getId());
            //需要转换为sysDept跟数据库对应  通过构建者模式 把dto的转移到pojo
        SysAclModule sysAclModule = SysAclModule.builder().name(sysAclModuleDto.getName())
                    .parentId(sysAclModuleDto.getParentId()).seq(sysAclModuleDto.getSeq())
                    .remark(sysAclModuleDto.getRemark()).status(sysAclModuleDto.getStatus()).build();
            //level
            //获得父类的level  父类的id为1  父类的lever  0    0-1
        sysAclModule.setLevel(LevelUtil.getLevel(getParentLevel(sysAclModuleDto.getParentId()),sysAclModuleDto.getParentId()));

            //操作人 TODO:得从session与request拿
        sysAclModule.setOperator(ThreadLocalCommon.getSysUser().getUsername());
        sysAclModule.setOperateTime(new Date());
        sysAclModule.setOperateIp(IPUtils.getIpAddress(ThreadLocalCommon.popHttpServletRequest()));
        sysAclModuleMapper.insertSelective(sysAclModule);

    }

    private String getParentLevel(Integer id) {
        SysAclModule sysAclModule = sysAclModuleMapper.selectLevelById(id);
        if(sysAclModule==null){
            return null;
        }
        return sysAclModule.getLevel();
    }

    private void checkAclModuleName(String name, Integer parentId, Integer id) {
        SysAclModule sysAclModule = sysAclModuleMapper.checkAclModuleName(name,parentId,id);
        if(sysAclModule !=null){
            throw new ParamException("部门名重复");
        }
    }

    @Override
    public void update(SysAclModuleDto sysAclModuleDto) {
        //看level是否改过  父部门的level+父部门的id生成
        //校验数据
        BeanValidator.check(sysAclModuleDto);
        //考虑能不能修改  部门名字不能重复
        checkAclModuleName(sysAclModuleDto.getName(),sysAclModuleDto.getParentId(),sysAclModuleDto.getId());
            //查询出修改之前之前的部门信息 获得level
        SysAclModule before = sysAclModuleMapper.selectByPrimaryKey(sysAclModuleDto.getId());
            //检验修改的部门是否存在
            Preconditions.checkNotNull(before, "修改的部门不存在");
            //需要转换为sysDept跟数据库对应  通过构建者模式 把dto的转移到pojo  修改的时候会多一个id
        SysAclModule after = SysAclModule.builder().name(sysAclModuleDto.getName())
                .parentId(sysAclModuleDto.getParentId()).seq(sysAclModuleDto.getSeq()).id(sysAclModuleDto.getId())
                .remark(sysAclModuleDto.getRemark()).status(sysAclModuleDto.getStatus()).build();
            //level
            //获得父类的level  父类的id为1  父类的lever  0    0-1
            after.setLevel(LevelUtil.getLevel(getParentLevel(sysAclModuleDto.getParentId()),sysAclModuleDto.getParentId()));
            //操作人 TODO:得从session与request拿
            after.setOperator(ThreadLocalCommon.getSysUser().getUsername());
            after.setOperateTime(new Date());
            after.setOperateIp(IPUtils.getIpAddress(ThreadLocalCommon.popHttpServletRequest()));
            updateWithChild(before,after,sysAclModuleDto.getId());
    }

    private void updateWithChild(SysAclModule before, SysAclModule after, Integer id) {
        String beforeLevel = before.getLevel();
        String afterLevel = after.getLevel();
        if(!beforeLevel.equals(afterLevel)){        //0  0-1%   0-10 0-12
           List<SysAclModule> list = sysAclModuleMapper.selectChildByLevel(LevelUtil.getLevel(beforeLevel,id),id);
            for (SysAclModule sysAclModule : list) {
                //"0-1-10-12","0-1","0-2"
                sysAclModule.setLevel(afterLevel+sysAclModule.getLevel().substring(beforeLevel.length()));
                //肯定该优化   存储过程
                sysAclModuleMapper.updateByPrimaryKey(sysAclModule);
            }
        }
        sysAclModuleMapper.updateByPrimaryKey(after);
    }

    @Override
    public List<SysAclModuleTreeDto> sysAclModuleTree(){
        List<SysAclModule> sysAclModuleList = sysAclModuleMapper.queryAll();
        if(CollectionUtils.isEmpty(sysAclModuleList)){
            return Lists.newArrayList();
        }
        List<SysAclModuleTreeDto> rootList = new ArrayList<>();
        //Map<String,List>
        Multimap<String, SysAclModuleTreeDto> multimap = ArrayListMultimap.create();
        SysAclModuleTreeDto sysAclModuleTreeDto;
        for (SysAclModule sysAclModule : sysAclModuleList) {
            //转换为树dto
            sysAclModuleTreeDto = SysAclModuleTreeDto.getSysAclModuleTreeDto(sysAclModule);
            if(sysAclModuleTreeDto.getLevel().equals(LevelUtil.ROOT_LEVEL)){//最高一级
                rootList.add(sysAclModuleTreeDto);
            }
            multimap.put(sysAclModuleTreeDto.getLevel(),sysAclModuleTreeDto);
        }
        //对rootList排序
        Collections.sort(rootList,comparator);
        getAclModuleTree(rootList,multimap);
        return rootList;
    }

    private void getAclModuleTree(List<SysAclModuleTreeDto> rootList, Multimap<String,SysAclModuleTreeDto> multimap) {
        String level;
        List<SysAclModuleTreeDto> childList;
        for (SysAclModuleTreeDto sysAclModuleTreeDto : rootList) {

            //拼接子类的level
            level = LevelUtil.getLevel(sysAclModuleTreeDto.getLevel(),sysAclModuleTreeDto.getId());
            childList = (List<SysAclModuleTreeDto>) multimap.get(level);
            if(!CollectionUtils.isEmpty(childList)){
                //child排序
                Collections.sort(childList,comparator);
                //设置到父类对象
                sysAclModuleTreeDto.setList(childList);
                //递归
                getAclModuleTree(childList,multimap);
            }
        }
    }

    Comparator comparator = new Comparator<SysAclModuleTreeDto>() {
        @Override
        public int compare(SysAclModuleTreeDto o1, SysAclModuleTreeDto o2) {
            return o1.getSeq()-o2.getSeq();
        }
    };
}
