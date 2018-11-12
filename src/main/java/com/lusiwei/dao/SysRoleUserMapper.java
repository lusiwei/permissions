package com.lusiwei.dao;

import com.lusiwei.pojo.SysRoleUser;

import java.util.List;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);



    List<SysRoleUser> queryUserByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);

    void insertUserByRoleId(Integer roleId);
}