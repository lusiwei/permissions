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
    @Length(min = 2, max = 10)
    private String name;
    private Integer parentId=0;
    @NotNull(message = "部门优先级不能为空")
    private Integer seq;
    @NotBlank(message = "备注不能为空")
    private String remark;
}
