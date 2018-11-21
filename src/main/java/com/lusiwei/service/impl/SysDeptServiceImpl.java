package com.lusiwei.service.impl;

import com.google.common.base.Preconditions;
import com.lusiwei.dao.SysDeptMapper;
import com.lusiwei.dao.SysUserMapper;
import com.lusiwei.dto.SysDeptDto;
import com.lusiwei.exception.ParamException;
import com.lusiwei.pojo.SysDept;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysDeptService;
import com.lusiwei.util.BeanValidator;
import com.lusiwei.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: lusiwei
 * @date: 2018/11/2 10:59
 * @description: 部门业务层
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;
    private final SysUserMapper sysUserMapper;

    @Autowired
    public SysDeptServiceImpl(SysDeptMapper sysDeptMapper, SysUserMapper sysUserMapper) {
        this.sysDeptMapper = sysDeptMapper;
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public void insert(SysDeptDto sysDeptDto) {
        //验证参数
        BeanValidator.check(sysDeptDto);
        String name = sysDeptDto.getName();
        Integer id = sysDeptDto.getId();
        Integer seq = sysDeptDto.getSeq();
        String remark = sysDeptDto.getRemark();
        if (checkDeptIsRepeat(sysDeptDto.getParentId(), name, id)) {
            //获取parentId
            Integer parentId = sysDeptDto.getParentId();
            //构造这模式把dto数据设置到pojo
            SysDept sysDept = SysDept.builder().name(name)
                    .parentId(parentId).seq(seq)
                    .remark(remark).build();

            //设置层级
            sysDept.setLevel(LevelUtil.getLevel(getParentLevel(parentId), parentId));
            //设置操作者 TODO:从session 和cookie拿
            sysDept.setOperator("lusiwei");
            //设置操作时间
            sysDept.setOperateTime(new Date());
            //设置操作者ip
            sysDept.setOperateIp("127.0.0.1");
            sysDeptMapper.insertSelective(sysDept);
        }
    }

    @Override
    public void update(SysDeptDto sysDeptDto) {
        BeanValidator.check(sysDeptDto);
        //如果前台传过来修改后的数据,查询数据库需要验证有没有重复的
        Integer parentId = sysDeptDto.getParentId();
        String name = sysDeptDto.getName();
        Integer id = sysDeptDto.getId();
        boolean isRepeat = checkDeptIsRepeat(parentId, name, id);
        if (isRepeat) {
            //查询修改之前的部门信息,获得level
            SysDept oldDeptInfo = sysDeptMapper.selectByPrimaryKey(id);
            //???? TODO: 检验修改的部门是否存在
            Preconditions.checkNotNull(oldDeptInfo, "修改的部门不存在");
            SysDept newDeptInfo = SysDept.builder().id(id).name(name)
                    .parentId(parentId).seq(sysDeptDto.getSeq())
                    .remark(sysDeptDto.getRemark()).build();
            newDeptInfo.setLevel(LevelUtil.getLevel(getParentLevel(parentId), parentId));
            newDeptInfo.setOperator("lusiwei");
            newDeptInfo.setOperateTime(new Date());
            newDeptInfo.setOperateIp("127.0.0.1");
            updateWithChild(oldDeptInfo, newDeptInfo, sysDeptDto.getId());
        }

    }

    /**
     * 删除部门
     *
     * @param deptId 部门id
     */
    @Override
    public boolean deleteById(Integer deptId) {
        //判断部门下是否有用户，如果有则不能删除，如果没有就可以删除
        if (!checkHasUser(deptId)) {
            sysDeptMapper.deleteByPrimaryKey(deptId);
            return true;
        }
        return false;
    }

    private boolean checkHasUser(Integer deptId) {
        List<SysUser> sysUserList=sysUserMapper.queryUserByDeptId(deptId);
        return !CollectionUtils.isEmpty(sysUserList);
    }


    private void updateWithChild(SysDept oldDeptInfo, SysDept newDeptInfo, Integer id) {
        String oldLevel = oldDeptInfo.getLevel();
        String newLevel = newDeptInfo.getLevel();
        //如果修改了level
        if (!oldLevel.equals(newLevel)) {
            sysDeptMapper.updateChildLevel(oldLevel, newLevel, id);
        }
        sysDeptMapper.updateByPrimaryKey(newDeptInfo);
    }

    /**
     * 获取parentLevel
     * 根据parentId 从数据库查询parentLevel
     */
    public String getParentLevel(Integer parentId) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(parentId);
        if (sysDept == null) {
            return null;
        }
        return sysDept.getLevel();
    }

    /**
     * 检查部门是否重复,不同部门的子部门下可以有相同部门,同一部门下的子部门不能相同
     */
    private boolean checkDeptIsRepeat(Integer parentId, String deptName, Integer id) {
        SysDept sysDept = sysDeptMapper.checkDeptIsRepeat(parentId, deptName, id);
        if (sysDept != null) {
            throw new ParamException("该部门名称已存在");
        }
        return true;
    }

}
