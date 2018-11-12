package com.lusiwei.service;

import com.lusiwei.dto.RoleUserDto;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 13:16
 * @Description:
 */
public interface SysRoleUserService {
    RoleUserDto queryUserByRoleId(Integer roleId);

    void insertUserByRoleId(Integer roleId, Integer[] userIds);
}
