package com.lusiwei.service.impl;

import com.lusiwei.common.PageCommon;
import com.lusiwei.dao.SysAclMapper;
import com.lusiwei.dao.SysRoleMapper;
import com.lusiwei.pojo.SysAcl;
import com.lusiwei.service.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysAclServiceImpl implements SysAclService {
    private final SysAclMapper sysAclMapper;
    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public SysAclServiceImpl(SysAclMapper sysAclMapper, SysRoleMapper sysRoleMapper) {
        this.sysAclMapper = sysAclMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public PageCommon<SysAcl> queryAclByModuleId(Integer aclModuleId, Integer pageSize, Integer pageNo) {
        pageSize = pageSize==null || pageSize<5 ?5:pageSize;
        pageNo = pageNo==null|| pageNo<0?1:pageNo;
        //查询总数
        long totalCount = sysAclMapper.selectCountByModuleId(aclModuleId);
        PageCommon<SysAcl> pageCommon = new PageCommon<SysAcl>();
        if(totalCount<=0){
            return pageCommon;
        }
        int begin = (pageNo-1)*pageSize;
        //查询数据
        List<SysAcl> aclList =  sysAclMapper.selectAclByModuleId(aclModuleId,begin,pageSize);
        pageCommon.setPageList(aclList);
        pageCommon.setTotalCount(totalCount);
        pageCommon.setCurPage(pageNo);
        pageCommon.setPageSize(pageSize);


        return pageCommon;
    }


}
