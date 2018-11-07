package com.lusiwei.service;

import com.lusiwei.dto.DeptLevelDto;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 21:55
 * @Description: 处理获取部门树的业务逻辑
 */
public interface GetDeptTreeService {
    /**
     * 获取部门树的方法
     */
    List<DeptLevelDto> getDeptTree();
}
