package com.lusiwei.dto;

import com.lusiwei.pojo.SysUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 13:52
 * @Description:
 */
@Setter
@Getter
@ToString
@Builder
public class RoleUserDto {
    List<SysUser>  selected;
    List<SysUser> unselected;
}
