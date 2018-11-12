package com.lusiwei.service;

import com.lusiwei.common.PageCommon;
import com.lusiwei.pojo.SysAcl;





public interface SysAclService {

   PageCommon<SysAcl> queryAclByModuleId(Integer aclModuleId, Integer pageSize, Integer pageNo);
}
