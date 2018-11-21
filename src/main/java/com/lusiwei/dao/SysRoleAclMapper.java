package com.lusiwei.dao;

import com.lusiwei.pojo.SysRoleAcl;

import java.util.List;

public interface SysRoleAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleAcl record);

    int insertSelective(SysRoleAcl record);

    SysRoleAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleAcl record);

    int updateByPrimaryKey(SysRoleAcl record);

    void deleteByRoleId(Integer roleId);

    List<SysRoleAcl> queryAllRoleByAclId(Integer aclId);
}