package com.lusiwei.service;

import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.PageUtilDto;
import com.lusiwei.dto.SysUserDto;
import com.lusiwei.pojo.SysUser;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 11:34
 * @Description:
 */
public interface SysUserService {
    void insert(SysUserDto sysDeptDto);

    void update(SysUserDto sysUserDto);

    ResultJson login(String account, String password);


    PageUtilDto queryAllUser(Integer curPage, Integer pageSize);

    void checkTelephone(String telephone, Integer id);

    void checkEmail(String mail, Integer id);

    List<SysUser> queryByDeptId(Integer deptId);
}
