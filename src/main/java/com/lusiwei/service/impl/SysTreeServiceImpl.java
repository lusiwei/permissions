package com.lusiwei.service.impl;

import com.google.common.collect.Lists;
import com.lusiwei.dao.SysDeptMapper;
import com.lusiwei.dto.SysDeptTreeDto;
import com.lusiwei.pojo.SysDept;
import com.lusiwei.service.SysTreeService;
import com.lusiwei.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 15:32
 * @Description:
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {
    private final SysDeptMapper sysDeptMapper;

    @Autowired
    public SysTreeServiceImpl(SysDeptMapper sysDeptMapper) {
        this.sysDeptMapper = sysDeptMapper;
    }

    @Override
    public List<SysDeptTreeDto> sysDeptTree() {

        List<SysDept> sysDepts = sysDeptMapper.queryAllSysDept();
        //转换为SysDeptTreeDto
        List<SysDeptTreeDto > dtoList = Lists.newArrayList();
        SysDeptTreeDto sysDeptTreeDto;
        for (SysDept sysDept : sysDepts) {
            sysDeptTreeDto = SysDeptTreeDto.getSysDeptTreeDto(sysDept);
            dtoList.add(sysDeptTreeDto);
        }
        return getDeptTree(dtoList);
    }
    private List<SysDeptTreeDto> getDeptTree(List<SysDeptTreeDto> dtoList) {
        if(CollectionUtils.isEmpty(dtoList)){
            return null;
        }
        //获取所有根节点
        List<SysDeptTreeDto> rootList = Lists.newArrayList();
        Map<String,List<SysDeptTreeDto>> levelMap = new HashMap<>();
        for (SysDeptTreeDto sysDeptTreeDto : dtoList) {
            String level = sysDeptTreeDto.getLevel();
            if(LevelUtil.ROOT_LEVEL.equals(level)){
                rootList.add(sysDeptTreeDto);
            }else{
                List<SysDeptTreeDto> levelList = levelMap.get(level);
                if(CollectionUtils.isEmpty(levelList)){
                    levelList = new ArrayList<>();
                    levelMap.put(level,levelList);
                }
                levelList.add(sysDeptTreeDto);
            }
        }
        Collections.sort(rootList, new Comparator<SysDeptTreeDto>() {
            @Override
            public int compare(SysDeptTreeDto o1, SysDeptTreeDto o2) {
                return  o1.getSeq()-o2.getSeq();
            }
        });
        fillDeptTree(rootList,levelMap);
        return rootList;
    }
    private void fillDeptTree(List<SysDeptTreeDto> rootList, Map<String,List<SysDeptTreeDto>> levelMap) {

        for(int i=0;i<rootList.size();i++){
            SysDeptTreeDto sysDeptTreeDto = rootList.get(i);
            String level = LevelUtil.getLevel(sysDeptTreeDto.getLevel(),sysDeptTreeDto.getId());
            List<SysDeptTreeDto> childList = levelMap.get(level);
            if(!CollectionUtils.isEmpty(childList)){
                sysDeptTreeDto.setList(childList);
                fillDeptTree(childList,levelMap);
            }

        }
    }
}
