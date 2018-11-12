package com.lusiwei.service;

import com.lusiwei.dto.SysAclModuleTreeDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/8 21:15
 * @Description:
 */
@Service
public interface SysCoreService {
    List<SysAclModuleTreeDto> coreTree(Integer roleId);
}
