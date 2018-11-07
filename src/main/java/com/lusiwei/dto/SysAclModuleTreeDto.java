package com.lusiwei.dto;

import com.lusiwei.pojo.SysAclModule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class SysAclModuleTreeDto extends SysAclModule implements Serializable {
    private List<SysAclModuleTreeDto> list;

    public static SysAclModuleTreeDto getSysAclModuleTreeDto(SysAclModule sysAclModule){
        SysAclModuleTreeDto sysAclModuleTreeDto = new SysAclModuleTreeDto();
        BeanUtils.copyProperties(sysAclModule,sysAclModuleTreeDto);
        return sysAclModuleTreeDto;
    }
}
