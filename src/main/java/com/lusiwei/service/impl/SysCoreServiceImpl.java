package com.lusiwei.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lusiwei.dao.SysAclMapper;
import com.lusiwei.dao.SysRoleMapper;
import com.lusiwei.dto.SysAclModuleTreeDto;
import com.lusiwei.dto.SysAclTreeDto;
import com.lusiwei.pojo.SysAcl;
import com.lusiwei.pojo.SysRole;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysAclModuleService;
import com.lusiwei.service.SysCoreService;
import com.lusiwei.util.ThreadLocalCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysCoreServiceImpl implements SysCoreService {

    private final SysAclModuleService sysAclModuleService;

    private final SysRoleMapper sysRoleMapper;

    private final SysAclMapper sysAclMapper;

    @Autowired
    public SysCoreServiceImpl(SysAclModuleService sysAclModuleService, SysRoleMapper sysRoleMapper, SysAclMapper sysAclMapper) {
        this.sysAclModuleService = sysAclModuleService;
        this.sysRoleMapper = sysRoleMapper;
        this.sysAclMapper = sysAclMapper;
    }

    @Override
    public List<SysAclModuleTreeDto> coreTree(Integer roleId) {
        //获得模块的tree
        List<SysAclModuleTreeDto> list = sysAclModuleService.sysAclModuleTree();
        //把权限点查询出来 
        //取出当前用户
        SysUser sysUser = ThreadLocalCommon.getSysUser();
        Preconditions.checkNotNull(sysUser,"请先登录");

        List<SysRole> roleList = queryRoleByUser(sysUser.getId());
        List<SysAcl> aclList = queryAclByRoleId(roleList);
        //取出当前用户拥有的所有不重复的acl_id
        Set<Integer> userAclId = getAclId(aclList);
        //取出当前角色所有的不重复的acl_id
        List<SysRole> sigleRoleList= new ArrayList<>();
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sigleRoleList.add(sysRole);
        List<SysAcl> currentAclList = queryAclByRoleId(sigleRoleList);
        //获得当前要授权的角色所拥有的所有权限点的id集合
        Set<Integer> roleAclId = getAclId(currentAclList);
        getTree(list,userAclId,roleAclId);
        return list;
    }

    private void getTree(List<SysAclModuleTreeDto> list, Set<Integer> userAclId, Set<Integer> roleAclId) {
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        List<SysAcl> sysAclList;
        SysAclTreeDto sysAclTreeDto;
        List<SysAclTreeDto> sysAclTreeDtoList;
        for (SysAclModuleTreeDto sysAclModuleTreeDto : list) {
            //根据模块id查询到所拥有的权限点
            sysAclList = sysAclMapper.queryAclByModuleId(sysAclModuleTreeDto.getId());
            sysAclTreeDtoList = Lists.newArrayList();
            for (SysAcl sysAcl : sysAclList) {
                sysAclTreeDto = SysAclTreeDto.getSysAclTreeDto(sysAcl);
                //如果用户拥有这个权限点 就设置true
                if(userAclId.contains(sysAcl.getId())){
                    sysAclTreeDto.setHasAcl(true);
                }
                //当前角色已经拥有这个权限点
                if(roleAclId.contains(sysAcl.getId())){
                    sysAclTreeDto.setChecked(true);
                }
                sysAclTreeDtoList.add(sysAclTreeDto);
            }
            sysAclModuleTreeDto.setAclList(sysAclTreeDtoList);
            //递归
            getTree(sysAclModuleTreeDto.getList(),userAclId,roleAclId);
        }

    }


    //查询到当前用户所拥有的所有角色
    private List<SysRole> queryRoleByUser(Integer userId){
        return  sysRoleMapper.queryRoleByUser(userId);
    }
    private  Set<Integer> getAclId(List<SysAcl> aclList){
        Set<Integer> aclSet = aclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        return aclSet;
    }
    //根据角色查询到所有权限
    private List<SysAcl> queryAclByRoleId(List<SysRole> roleList){
        if(CollectionUtils.isEmpty(roleList)){
            return Lists.newArrayList();
        }
        return  sysAclMapper.queryAclByRoleId(roleList);
    }

}

