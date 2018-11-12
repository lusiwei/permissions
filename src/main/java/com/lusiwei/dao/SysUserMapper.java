package com.lusiwei.dao;

import com.lusiwei.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int checkEmail(@Param("mail") String mail, @Param("id") Integer id);

    int checkTelephone(@Param("telephone") String telephone, @Param("id") Integer id);

    SysUser login(@Param("account") String account);

    List<SysUser> queryAllUser(@Param("limitBegin") Integer limitBegin, @Param("pageSize") Integer pageSize);

    long queryCount();

    List<SysUser> queryByDeptId(@Param("deptId") Integer deptId);

    List<SysUser> queryUnselectedUser(List<Integer> list);

    List<SysUser> querySelectedUser(List<Integer> list);
}