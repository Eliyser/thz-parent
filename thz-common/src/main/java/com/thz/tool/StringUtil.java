package com.thz.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author haien
 * @Description 字符串处理工具类
 * @Date 2018/12/1
 **/
public class StringUtil {

    /**
     * @Author haien
     * @Description 判断字符串是否为null、“ ”、“null"
     * @Date 2018/12/1
     * @Param [str]
     * @return boolean
     **/
    public static boolean isNull(String str){
        if(null==str){
            return true;
        }else if(str.trim().equals("")){
            return true;
        }else if(str.trim().toLowerCase().equals("null")){
            return true;
        }
        return false;
    }

    /**
     * @Author haien
     * @Description 验证是否时间
     * @Date 2018/12/2
     * @Param [str]
     * @return boolean
     **/
    public static boolean isTime(String str){
        Pattern pattern=Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        Matcher matcher=pattern.matcher(str);
        return matcher.matches();
    }
}


















