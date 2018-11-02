package com.xmcc.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 10:37
 * @Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptDto implements Serializable {

    private Integer id;

    @NotBlank(message = "部门名不能为空")
    @Length(min = 2, max = 15, message = "部门名称需要在2,15 个字符之间")

    private String name;

    private Integer parentId = 0;

    @NotNull(message = "部门优先级不能为空")
    private Integer seq;

    @Length(max = 150, message = "备注的长度不能超过150个字")
    private String remark;
}
