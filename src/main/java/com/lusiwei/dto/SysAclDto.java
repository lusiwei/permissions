package com.lusiwei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: lusiwei
 * @Date: 2018/11/7 15:09
 * @Description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SysAclDto implements Serializable {
    private Integer id;

    @NotBlank(message = "商品编码不能为空")
    private String code;
    @NotBlank(message = "商品名字不能为空")
    private String name;
    @NotNull(message = "权限模块名不能为空")
    private Integer aclModuleId;

    @NotBlank(message = "url不能为空")
    private String url;

    @NotNull(message = "商品类型不能为空")
    private Integer type;

    @NotNull(message = "status不能为空")
    private Integer status;

    @NotNull(message = "seq不能为空")
    private Integer seq;

    private String remark;
}
