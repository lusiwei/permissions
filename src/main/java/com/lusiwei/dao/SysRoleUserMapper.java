package com.lusiwei.dao;

import com.lusiwei.pojo.SysRoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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

    Set<SysRoleUser> queryRoleUserByRoleId(@Param("set") Set<Integer> roleIdSet);
}