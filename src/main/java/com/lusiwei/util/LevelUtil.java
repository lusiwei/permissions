package com.lusiwei.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 10:48
 * @Description:
 */
public class LevelUtil {
    public static final String ROOT_LEVEL = "0";
    public static final String SEPARATOR = "-";

    /**
     * 通过parentLevel 和parentId 拼接当前部门的level
     */
    public static String getLevel(String parentLevel, Integer parentId) {
        if (parentLevel == null) {
            return ROOT_LEVEL;
        }
        return StringUtils.join(parentLevel,SEPARATOR,parentId);
    }
}
