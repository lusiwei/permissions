package com.lusiwei.dao;

import com.lusiwei.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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

    Set<SysRole> queryRoleByRoleId(@Param("set") Set<Integer> roleIdSet);
}