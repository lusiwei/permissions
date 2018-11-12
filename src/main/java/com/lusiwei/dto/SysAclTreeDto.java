package com.lusiwei.dto;

import com.lusiwei.pojo.SysAcl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;



@Setter
@Getter
public class SysAclTreeDto extends SysAcl {

    //1.判断当前用户是否能操作该权限点  标记当前登录用户是否能授予这个权限
    private boolean hasAcl = false;

    //2.判断当前角色是否已经拥有了该权限点 判断复选框是否勾中
    private boolean isChecked = false;

    public static SysAclTreeDto getSysAclTreeDto(SysAcl sysAcl){
        SysAclTreeDto sysAclTreeDto = new SysAclTreeDto();
        BeanUtils.copyProperties(sysAcl,sysAclTreeDto);
        return sysAclTreeDto;
    }

}
