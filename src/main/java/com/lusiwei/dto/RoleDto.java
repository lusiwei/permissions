package com.lusiwei.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: lusiwei
 * @Date: 2018/11/8 09:55
 * @Description:
 */
@Setter
@Getter
@ToString
public class RoleDto implements Serializable {

    private Integer id;
    @NotBlank(message = "角色名不能為空")
    private String name;
    private Integer type;
    @NotNull(message = "status不能為空")
    private Integer status;
    @Length(max = 150,message = "備註不能超過150字")
    private String remark;

}
