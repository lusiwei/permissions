package com.lusiwei.service.impl;

import com.lusiwei.dao.SysRoleUserMapper;
import com.lusiwei.dao.SysUserMapper;
import com.lusiwei.dto.RoleUserDto;
import com.lusiwei.pojo.SysRoleUser;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysRoleUserService;
import com.lusiwei.util.IPUtils;
import com.lusiwei.util.ThreadLocalCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 13:19
 * @Description:
 */
@Service
public class RoleUserServiceImpl implements SysRoleUserService {
    private final SysRoleUserMapper sysRoleUserMapper;
    private final SysUserMapper sysUserMapper;

    @Autowired
    public RoleUserServiceImpl(SysRoleUserMapper sysRoleUserMapper, SysUserMapper sysUserMapper) {
        this.sysRoleUserMapper = sysRoleUserMapper;
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public RoleUserDto queryUserByRoleId(Integer roleId) {
        List<SysRoleUser> sysUsers=sysRoleUserMapper.queryUserByRoleId(roleId);
        List<Integer> list=new ArrayList<>();
        //查詢所有用戶
        for (SysRoleUser sysUser : sysUsers) {
            list.add(sysUser.getUserId());
        }
        System.out.println("-------------------------");
        System.out.println(list);
        List<SysUser> selectedUser=null;
        if (list.size()>0){
            selectedUser=sysUserMapper.querySelectedUser(list);
        }
        List<SysUser> unselectedUser=sysUserMapper.queryUnselectedUser(list);
        RoleUserDto roleUserDto=RoleUserDto.builder().selected(selectedUser).unselected(unselectedUser).build();
        return roleUserDto;
    }

    @Override
    public void insertUserByRoleId(Integer roleId, Integer[] userIds) {
        sysRoleUserMapper.deleteByRoleId(roleId);
        for (Integer userId : userIds) {
            SysRoleUser sysRoleUser = SysRoleUser.builder().roleId(roleId).userId(userId).build();
            sysRoleUser.setOperator(ThreadLocalCommon.getSysUser().getUsername());
            sysRoleUser.setOperateIp(IPUtils.getIpAddress(ThreadLocalCommon.popHttpServletRequest()));
            sysRoleUser.setOperateTime(new Date());
            sysRoleUserMapper.insertSelective(sysRoleUser);
        }
    }
}
