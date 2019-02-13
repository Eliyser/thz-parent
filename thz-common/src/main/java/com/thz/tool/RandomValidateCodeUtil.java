package com.thz.tool;

import java.util.Random;

/**
 * @Author haien
 * @Description 生成随机验证码
 * @Date 2019/2/9
 **/
public class RandomValidateCodeUtil {
    /**
     * @Author haien
     * @Description 生成6位数字随机验证码
     * @Date 2019/2/10
     * @Param []
     * @return java.lang.String
     **/
    public static String getVerifyCode(){
        return String.valueOf(new Random().nextInt(899999)+100000);
    }
}




























