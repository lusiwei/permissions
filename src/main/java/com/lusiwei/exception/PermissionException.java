package com.lusiwei.exception;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Author: lusiwei
 * @Date: 2018/10/31 20:51
 * @Description:
 */
@Getter
public class PermissionException extends RuntimeException implements Serializable {
    public String message;

    public PermissionException(String message) {
        super(message);
        this.message=message;
    }
}
