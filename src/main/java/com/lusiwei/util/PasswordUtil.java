package com.lusiwei.util;

public class PasswordUtil {
    //设置密码 一般是把字母 数字 随机
    //特殊的密码要求 符号

    private static final String[] charactor={
            "a","b","c","d","e","A"
    };
    private static final String[] num = {
            "2","3","4","5","6","7","8","9"
    };

    public static String getPassword(){
        //定义密码长度 5+6 4+7
        int length = (int) (Math.random() * 5 + 1) + 6;
        boolean flag = false;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if(flag){
                stringBuffer.append(charactor[(int) (Math.random() * charactor.length)]);
            }else{
                stringBuffer.append(num[(int) (Math.random() * num.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }
}
