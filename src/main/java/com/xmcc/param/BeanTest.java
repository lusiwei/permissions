package com.xmcc.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/1 10:04
 * @Description:
 */
@Getter
@Setter
public class BeanTest {

    @NotBlank
    private String username;

    @NotNull
    private Integer age;

    @NotEmpty
    private List list;



}
