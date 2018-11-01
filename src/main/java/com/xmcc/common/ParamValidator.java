package com.xmcc.common;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @Author: lusiwei
 * @Date: 2018/11/1 10:11
 * @Description:
 */
public class ParamValidator {
    private static ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
    public static <T> Map<String,String> validateBean(T t,Class...classes){
        Validator validator = validatorFactory.getValidator();
        Map<String,String> errorsMap= new HashMap<>();
        if (t == null) {
            return Collections.emptyMap();
        }
        Set<ConstraintViolation<T>> validate = validator.validate(t, classes);
        Iterator<ConstraintViolation<T>> iterator = validate.iterator();
        ConstraintViolation<T> constraintViolation;
        while(iterator.hasNext()) {
            constraintViolation = iterator.next();
            errorsMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return errorsMap;
    }
}
