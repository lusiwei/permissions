package com.lusiwei.dao;

import com.lusiwei.pojo.SysAcl;
import com.lusiwei.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    long selectCountByModuleId(@Param("aclModuleId") Integer aclModuleId);

    List<SysAcl> selectAclByModuleId(@Param("aclModuleId") Integer aclModuleId, @Param("begin") int begin, @Param("pageSize") Integer pageSize);

    List<SysAcl> queryAclByModuleId(Integer id);

    List<SysAcl> queryAclByRoleId(@Param("roleList") List<SysRole> roleList);

    List<SysAcl> queryAclByUrl(String url);

    List<SysAcl> queryAclByAclModuleId(Integer aclModuleId);
}