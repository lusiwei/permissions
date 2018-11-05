package com.xmcc.dao;

import com.xmcc.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int checkEmail(@Param("mail") String mail, @Param("id") Integer id);

    int checkTelephone(@Param("telephone") String telephone, @Param("id") Integer id);
}