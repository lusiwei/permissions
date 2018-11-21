package com.lusiwei.service;

import com.lusiwei.dto.SysDeptDto;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 10:58
 * @Description:
 */
public interface SysDeptService {
    void insert(SysDeptDto sysDeptDto);
    void update(SysDeptDto sysDept);

    boolean deleteById(Integer deptId);
}
