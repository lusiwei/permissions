package com.xmcc.service;

import com.xmcc.dto.SysDeptDto;
import com.xmcc.dto.SysUserDto;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 11:34
 * @Description:
 */
public interface SysUserService {
    void insert(SysUserDto sysDeptDto);

    void update(SysUserDto sysUserDto);

    void checkTelephone(String telephone, Integer id);

    void checkEmail(String mail, Integer id);
}
