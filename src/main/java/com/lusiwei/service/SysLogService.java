package com.lusiwei.service;

import com.lusiwei.pojo.SysAcl;
import com.lusiwei.pojo.SysRole;
import com.lusiwei.pojo.SysUser;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/14 15:06
 * @Description:
 */
public interface SysLogService {
    /**
     * 用户模块
     */
    void saveLogUser(SysUser oldSysUser, SysUser newSysUser);

    /**
     * 角色模块
     */
    void saveLogRole(SysRole oldSysRole, SysRole newSysRole);

    /**
     * 权限模块
     */
    void saveLogAcl(SysAcl oldSysAcl, SysAcl newSysAcl);

    /**
     * 角色用户模块
     */
    void saveLogRoleUser(Integer userId, List<Integer> oldRoleIds, List<Integer> newRoleIds);

    /**
     * 角色权限模块
     *
     * @param roleId    角色id
     * @param oldAclIds 旧的aclId
     * @param newAclIds 新的aclId
     */
    void saveLogRoleAcl(Integer roleId, List<Integer> oldAclIds, List<Integer> newAclIds);
}
