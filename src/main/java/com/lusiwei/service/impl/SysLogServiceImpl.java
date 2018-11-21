package com.lusiwei.service.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.lusiwei.dao.SysLogMapper;
import com.lusiwei.pojo.SysAcl;
import com.lusiwei.pojo.SysLogWithBLOBs;
import com.lusiwei.pojo.SysRole;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysLogService;
import com.lusiwei.util.Constant;
import com.lusiwei.util.IPUtils;
import com.lusiwei.util.JsonUtil;
import com.lusiwei.util.RequestHolder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: lusiwei
 * @date : 2018/11/14 15:20
 * @description : 保存操作日志
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;
    private String oldValue = "";
    private String newValue = "";

    @Autowired
    public SysLogServiceImpl(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    @Override
    public void saveLogUser(SysUser oldSysUser, SysUser newSysUser) {
        Integer targetId;
        //如果oldSysUser为空,则是增加用户
        targetId = oldSysUser == null ? newSysUser.getId() : oldSysUser.getId();
        oldValue = oldSysUser == null ? "" : JsonUtil.object2String(oldSysUser);
        newValue = newSysUser == null ? "" : JsonUtil.object2String(newSysUser);
        SysLogWithBLOBs sysLogWithBLOBs = setValue(targetId, Constant.LOG_USER_TYPE, oldValue, newValue);
        sysLogMapper.insertSelective(sysLogWithBLOBs);
    }

    @Override
    public void saveLogRole(SysRole oldSysRole, SysRole newSysRole) {
        Integer targetId = oldSysRole == null ? newSysRole.getId() : oldSysRole.getId();
        oldValue = oldSysRole == null ? "" : JsonUtil.object2String(oldSysRole);
        newValue = newSysRole == null ? "" : JsonUtil.object2String(newSysRole);
        SysLogWithBLOBs sysLogWithBLOBs = setValue(targetId, Constant.LOG_ROLE_TYPE, oldValue, newValue);
        sysLogMapper.insertSelective(sysLogWithBLOBs);
    }

    @Override
    public void saveLogAcl(SysAcl oldSysAcl, SysAcl newSysAcl) {
        Integer targetId = oldSysAcl == null ? newSysAcl.getId() : oldSysAcl.getId();
        oldValue = oldSysAcl == null ? "" : JsonUtil.object2String(oldSysAcl);
        newValue = newSysAcl == null ? "" : JsonUtil.object2String(newSysAcl);
        SysLogWithBLOBs sysLogWithBLOBs = setValue(targetId, Constant.LOG_ACL_TYPE, oldValue, newValue);
        sysLogMapper.insertSelective(sysLogWithBLOBs);
    }

    @Override
    public void saveLogRoleUser(Integer userId, List<Integer> oldRoleIds, List<Integer> newRoleIds) {

    }

    @Override
    public void saveLogRoleAcl(Integer roleId, List<Integer> oldAclIds, List<Integer> newAclIds) {
        Preconditions.checkNotNull(roleId, "角色不存在");
        if (!CollectionUtils.isEmpty(oldAclIds)) {
            oldValue = Joiner.on(",").join(oldAclIds);
        }
        if (!CollectionUtils.isEmpty(newAclIds)) {
            newValue = Joiner.on(",").join(newAclIds);
        }
        SysLogWithBLOBs sysLogWithBLOBs = setValue(roleId, Constant.LOG_ROLE_USER_TYPE, oldValue, newValue);
        sysLogMapper.insertSelective(sysLogWithBLOBs);
    }

    private SysLogWithBLOBs setValue(Integer targetId, Integer type, String oldValue, String newValue) {
        SysLogWithBLOBs sysLogWithBLOBs = new SysLogWithBLOBs();
        sysLogWithBLOBs.setTargetId(targetId);
        sysLogWithBLOBs.setType(type);
        sysLogWithBLOBs.setOldValue(oldValue);
        sysLogWithBLOBs.setNewValue(newValue);
        sysLogWithBLOBs.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLogWithBLOBs.setOperateTime(new Date());
        sysLogWithBLOBs.setOperateIp(IPUtils.getIpAddress(RequestHolder.getHttpServletRequest()));
        sysLogWithBLOBs.setStatus(1);
        return sysLogWithBLOBs;
    }
}
