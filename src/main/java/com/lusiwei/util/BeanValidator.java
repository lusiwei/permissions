package com.lusiwei.util;

import com.google.common.collect.Lists;
import com.lusiwei.exception.ParamException;
import org.apache.commons.collections.MapUtils;

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
public class BeanValidator {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static Map<String, String> errorsMap = new HashMap<>();

    private static <T> Map<String, String> validateBean(T t, Class... classes) {
        Validator validator = validatorFactory.getValidator();
        if (t == null) {
            return Collections.emptyMap();
        }
        Set<ConstraintViolation<T>> validate = validator.validate(t, classes);
        Iterator<ConstraintViolation<T>> iterator = validate.iterator();
        ConstraintViolation<T> constraintViolation;
        while (iterator.hasNext()) {
            constraintViolation = iterator.next();
            errorsMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        validate.clear();
        return errorsMap;
    }

    /**
     * 校验单个集合
     *
     * @param <T>
     * @param collection
     * @return
     */
    private static <T> Map<String, String> validateList(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyMap();
        }
        Iterator<T> iterator = collection.iterator();
        T bean;
//        Class[] classes = new Class[0];
        while (errorsMap.isEmpty() && iterator.hasNext()) {
            bean = iterator.next();
            errorsMap = validateBean(bean, new Class[0]);
        }
        return errorsMap;
    }

    /**
     * 一个对象或多个对象
     */
    public static Map<String, String> validateObject(Object object, Object... objects) {
        if (object == null&&objects==null) {
            return Collections.emptyMap();
        }
        if (objects == null || objects.length == 0) {
            if (object instanceof Collection) {
                errorsMap=validateList((Collection) object);
            }else {
                errorsMap=validateBean(object);
            }
        }else {
            List<Object> objects1 = Lists.asList(object, objects);
            errorsMap=validateList(objects1);
        }
        return errorsMap;
    }

    public static void check(Object object, Object... objects) {
        errorsMap.clear();
        errorsMap = validateObject(object, objects);
        if (MapUtils.isNotEmpty(errorsMap)) {
            throw new ParamException(errorsMap.toString());
        }
    }
}
