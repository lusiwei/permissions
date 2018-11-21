package com.lusiwei.dto;

import com.lusiwei.pojo.SysRole;
import com.lusiwei.pojo.SysUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * @Author: lusiwei
 * @Date: 2018/11/13 17:06
 * @Description:
 */
@Setter
@Getter
@ToString
@Builder
public class RoleUserSetDto {
    private Set<SysRole> sysRoleSet;
    private Set<SysUser> sysUserSet;
}
