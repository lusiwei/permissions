package com.lusiwei.service;

import com.lusiwei.dto.RoleUserSetDto;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 11:08
 * @Description:
 */
public interface SysRoleAclService {
    void saveAcls(Integer roleId, Integer[] aclIds);

    RoleUserSetDto queryAllRoleByAclId(Integer aclId);
}
