package com.lusiwei.service.impl;

import com.lusiwei.dao.SysRoleAclMapper;
import com.lusiwei.pojo.SysRoleAcl;
import com.lusiwei.service.SysRoleAclService;
import com.lusiwei.util.IPUtils;
import com.lusiwei.util.ThreadLocalCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 11:08
 * @Description:
 */
@Service
public class RoleAclServiceImpl implements SysRoleAclService {
    private final SysRoleAclMapper sysRoleAclMapper;

    @Autowired
    public RoleAclServiceImpl(SysRoleAclMapper sysRoleAclMapper) {
        this.sysRoleAclMapper = sysRoleAclMapper;
    }

    @Override
    public void saveAcls(Integer roleId, Integer[] aclIds) {
        //删除
        sysRoleAclMapper.deleteByRoleId(roleId);
        //插入
        for (Integer aclId : aclIds) {
            SysRoleAcl sysRoleAcl = SysRoleAcl.builder().roleId(roleId).aclId(aclId).build();
            sysRoleAcl.setOperator(ThreadLocalCommon.getSysUser().getUsername());
            sysRoleAcl.setOperateIp(IPUtils.getIpAddress(ThreadLocalCommon.popHttpServletRequest()));
            sysRoleAcl.setOperateTime(new Date());
            sysRoleAclMapper.insertSelective(sysRoleAcl);
        }
    }
}
