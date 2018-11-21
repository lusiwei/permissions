package com.lusiwei.service;

import com.lusiwei.dto.SysAclModuleTreeDto;
import com.lusiwei.pojo.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: lusiwei
 * @Date: 2018/11/8 21:15
 * @Description:
 */
@Service
public interface SysCoreService {

    /**
     * @param roleId 角色id
     * @return 返回用户角色树
     */
    List<SysAclModuleTreeDto> coreTree(Integer roleId);


    /** 判断该URL是否有权限访问
     * @param url 访问的url
     * @return boolean
     */
    boolean hasUrlAcl(String url);

    Set<Integer> getAllAclByUser(SysUser currentUser);

    Set<String> getAllAclByUserId(Integer userId);
}
