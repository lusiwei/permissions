package com.lusiwei.dao;

import com.lusiwei.pojo.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> queryAllSysDept();

    SysDept checkDeptIsRepeat(@Param("parentId") Integer parentId, @Param("name") String deptName, @Param("id") Integer deptId);

    int updateChildLevel(@Param("oldLevel")String oldLevel, @Param("newLevel") String newLevel, @Param("id") Integer id);
}