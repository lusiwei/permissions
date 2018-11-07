package com.lusiwei.dao;

import com.lusiwei.pojo.SysAclModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);

    List<SysAclModule> selectChildByLevel(@Param("level") String level, @Param("id") Integer id);

    List<SysAclModule> queryAll();

    SysAclModule selectLevelById(Integer id);

    SysAclModule checkAclModuleName(@Param("name") String name, @Param("parentId") Integer parentId, @Param("id") Integer id);
}