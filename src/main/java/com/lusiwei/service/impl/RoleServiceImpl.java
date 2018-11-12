package com.lusiwei.service.impl;

import com.lusiwei.dao.SysRoleMapper;
import com.lusiwei.dto.RoleDto;
import com.lusiwei.exception.ParamException;
import com.lusiwei.pojo.SysRole;
import com.lusiwei.service.RoleService;
import com.lusiwei.util.BeanValidator;
import com.lusiwei.util.IPUtils;
import com.lusiwei.util.ThreadLocalCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/8 10:01
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public void insert(RoleDto roleDto) {
        BeanValidator.check(roleDto);
        checkName(roleDto.getName());
        SysRole sysRole=SysRole.builder().name(roleDto.getName()).type(roleDto.getType())
                .status(roleDto.getStatus()).remark(roleDto.getRemark()).build();
        sysRole.setOperator(ThreadLocalCommon.getSysUser().getUsername());
        sysRole.setOperateIp(IPUtils.getIpAddress(ThreadLocalCommon.popHttpServletRequest()));
        sysRole.setOperateTime(new Date());
        sysRoleMapper.insertSelective(sysRole);
    }



    @Override
    public void update(RoleDto roleDto) {
        BeanValidator.check(roleDto);
        checkExist(roleDto.getId());
        SysRole sysRole=SysRole.builder().id(roleDto.getId()).name(roleDto.getName()).type(roleDto.getType())
                .status(roleDto.getStatus()).remark(roleDto.getRemark()).build();
        sysRole.setOperator(ThreadLocalCommon.getSysUser().getUsername());
        sysRole.setOperateIp(IPUtils.getIpAddress(ThreadLocalCommon.popHttpServletRequest()));
        sysRole.setOperateTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);

    }

    private void checkExist(Integer id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRole==null){
            throw new ParamException("要修改的角色不存在");
        }
    }
    private void checkName(String name) {
        SysRole sysRole=sysRoleMapper.queryRoleByName(name);
        if (sysRole != null) {
            throw new ParamException("你要插入的角色已存在");
        }

    }

    @Override
    public List<SysRole> queryAllRole() {
        List<SysRole> sysRoleList=sysRoleMapper.queryAllRole();
        return sysRoleList;
    }

}
