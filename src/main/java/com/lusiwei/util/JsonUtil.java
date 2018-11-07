package com.lusiwei.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * @Author: lusiwei
 * @Date: 2018/11/1 15:35
 * @Description:
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();

    /**
     * 对象转字符串
     * @param t
     * @param <T>
     * @return
     */
    public  static <T> String object2String(T t) {
        if (t == null) {
            return null;
        }else {
            try {
                return OBJECT_MAPPER.writeValueAsString(t);
            } catch (JsonProcessingException e) {
                log.warn("json2string exception--Object{},Exception{}",t,e);
                return null;
            }
        }
    }
    /**
     * 字符串转对象
     */
    public static <T> T string2Object(String string, TypeReference<T> tTypeReference){
        if (string == null) {
            return null;
        }else {
            try {
                return OBJECT_MAPPER.readValue(string,tTypeReference);
            } catch (IOException e) {
                log.warn("jsonUtil:string2object exception,String:{},TypeReference:{},Exceptoin:{}",string,tTypeReference,e);
                return null;
            }
        }

    }
}
