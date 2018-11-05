package com.xmcc.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 11:35
 * @Description:
 */
@Setter
@Getter
public class SysUserDto {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    @Length(min = 2, max = 15)
    private String username;
    @NotBlank(message = "电话号码不能为空")
    @Length(min = 11, max = 11)
    private String telephone;
    @NotBlank(message = "邮箱不能为空")
    @Length(min = 5, max = 20)
    private String mail;
    @NotNull(message = "部门ID不能为空")
    private Integer deptId;
    @Min(value = 0, message = "状态，1：正常，0：冻结状态，2：删除")
    @Max(value = 2, message = "状态，1：正常，0：冻结状态，2：删除")
    private Integer status;
    @Length(max = 150, message = "备注不能超过150字")
    private String remark;

}
