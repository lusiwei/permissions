package com.xmcc.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lusiwei
 * @Date: 2018/10/31 20:13
 * @Description:
 */
@Setter
@Getter
public class ResultJson {
    /**
     * 请求是否成功
     */
    private boolean result;
    /**
     * 返回的信息
     */
    private String message;
    /**
     * 携带的数据
     */
    private Object data;

    public ResultJson(boolean b){
        this.result=b;
    }

    /**
     * 只获取请求成功与否
     * @return
     */
    public static ResultJson success(){
        ResultJson resultJson=new ResultJson(true);
        return resultJson;

    }

    /**
     * 获取请求成功的状态,信息及携带的数据
     * @param message
     * @param data
     * @return
     */
    public static ResultJson success(String message, Object data) {
        ResultJson resultJson=new ResultJson(true);
        resultJson.setMessage(message);
        resultJson.setData(data);
        return resultJson;
    }

    /**
     * 获取请求成功的状态及信息
     * @param data
     * @return
     */
    public static ResultJson success(Object data) {
        ResultJson resultJson=new ResultJson(true);
        resultJson.setData(data);
        return resultJson;
    }
    /**
     * 返回失败信息
     */
    public static ResultJson failed(String message){
        ResultJson resultJson = new ResultJson(false);
        resultJson.setMessage(message);
        return resultJson;
    }
    /**
     * toMap
     */
    public Map<String,Object> toMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result",result);
        map.put("message", message);
        map.put("data", data);
        return map;
    }



}
