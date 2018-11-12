package com.lusiwei.dao;

import com.lusiwei.pojo.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> queryAllRole();

    SysRole queryRoleByName(String name);

    List<SysRole> queryRoleByUser(Integer userId);
}