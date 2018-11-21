package com.lusiwei.service.impl;

import com.lusiwei.dao.SysRoleAclMapper;
import com.lusiwei.dao.SysRoleMapper;
import com.lusiwei.dao.SysRoleUserMapper;
import com.lusiwei.dao.SysUserMapper;
import com.lusiwei.dto.RoleUserSetDto;
import com.lusiwei.pojo.SysRole;
import com.lusiwei.pojo.SysRoleAcl;
import com.lusiwei.pojo.SysRoleUser;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysRoleAclService;
import com.lusiwei.util.IPUtils;
import com.lusiwei.util.RequestHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 11:08
 * @Description:
 */
@Service
public class RoleAclServiceImpl implements SysRoleAclService {
    private final SysRoleAclMapper sysRoleAclMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysRoleUserMapper sysRoleUserMapper;
    private final SysUserMapper sysUserMapper;

    @Autowired
    public RoleAclServiceImpl(SysRoleAclMapper sysRoleAclMapper, SysRoleMapper sysRoleMapper, SysRoleUserMapper sysRoleUserMapper, SysUserMapper sysUserMapper) {
        this.sysRoleAclMapper = sysRoleAclMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysRoleUserMapper = sysRoleUserMapper;
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public void saveAcls(Integer roleId, Integer[] aclIds) {
        //删除
        sysRoleAclMapper.deleteByRoleId(roleId);
        //插入
        for (Integer aclId : aclIds) {
            SysRoleAcl sysRoleAcl = SysRoleAcl.builder().roleId(roleId).aclId(aclId).build();
            sysRoleAcl.setOperator(RequestHolder.getCurrentUser().getUsername());
            sysRoleAcl.setOperateIp(IPUtils.getIpAddress(RequestHolder.getHttpServletRequest()));
            sysRoleAcl.setOperateTime(new Date());
            sysRoleAclMapper.insertSelective(sysRoleAcl);
        }
    }

    @Override
    public RoleUserSetDto queryAllRoleByAclId(Integer aclId) {
        //根据权限点id查出权限点
        List<SysRoleAcl> sysAclList = sysRoleAclMapper.queryAllRoleByAclId(aclId);
        //将查出来的roleId放到set中
        Set<Integer> roleIdSet = sysAclList.stream().map(SysRoleAcl::getRoleId).collect(Collectors.toSet());
        //根据roleId查对应的用户
        //TODO:
        Set<SysRoleUser> sysRoleUserSet = sysRoleUserMapper.queryRoleUserByRoleId(roleIdSet);
        //生成userId set
        Set<Integer> userIdSet = sysRoleUserSet.stream().map(SysRoleUser::getUserId).collect(Collectors.toSet());
        //根据userId 查出所有的user
        Set<SysUser> sysUserSet=sysUserMapper.queryByUserIds(userIdSet);
        //根据角色id查对应的角色
        Set<SysRole> sysRoleSet = sysRoleMapper.queryRoleByRoleId(roleIdSet);
        //将查到的角色名字作为set集合返回
        return RoleUserSetDto.builder().sysRoleSet(sysRoleSet).sysUserSet(sysUserSet).build();
    }
}
