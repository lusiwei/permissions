package com.lusiwei.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class SysAclModuleDto implements Serializable {

    private Integer id;
    @NotBlank(message = "权限模块名不能为空")
    @Length(min=3,max = 15)
    private String name;

    private Integer parentId=0;

    @NotNull(message = "权限模块优先级不能为空")
    private Integer seq;
    @NotNull(message = "状态，1：正常，0：冻结")
    @Min(0)
    @Max(1)
    private Integer status;
    @NotBlank(message = "权限模块备注不能为空")
    @Length(min=1,max=200,message = "权限模块说明1-200")
    private String remark;

}
