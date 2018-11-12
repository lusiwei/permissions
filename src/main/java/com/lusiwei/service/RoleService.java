package com.lusiwei.service;

import com.lusiwei.dto.RoleDto;
import com.lusiwei.pojo.SysRole;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/8 10:01
 * @Description:
 */
public interface RoleService {
    void insert(RoleDto roleDto);

    void update(RoleDto roleDto);

    List<SysRole> queryAllRole();
}
