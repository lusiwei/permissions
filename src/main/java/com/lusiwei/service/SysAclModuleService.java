package com.lusiwei.service;

import com.lusiwei.dto.SysAclModuleDto;
import com.lusiwei.dto.SysAclModuleTreeDto;

import java.util.List;


public interface SysAclModuleService {
    void insert(SysAclModuleDto sysAclModuleDto);

    void update(SysAclModuleDto sysAclModuleDto);

    List<SysAclModuleTreeDto> sysAclModuleTree();

    boolean deleteByAclId(Integer aclId);
}
