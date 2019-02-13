package com.thz.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 合法性校验工具
 * 
 * @author Administrator
 *
 */
public class ValidatorUtil {

	/**
	 * 功能：手机号验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 判断密码格式是否正确(只能是数字或者字母，长度为[6,12])
	 * @param passwd
	 * @return
	 * @author wyongjian
	 * @date 2014-11-18
	 */
	public static boolean isPasswd(String passwd){
		if(passwd.length()<6 || passwd.length()>12)return false;
		String regEx="^[A-Za-z0-9_]+$";
	    Pattern p=Pattern.compile(regEx);
	    Matcher m=p.matcher(passwd);
	    return m.matches();
	}

	/**
	 * 判断用户名格式是否正确(只能是数字、字母或者下划线，[6,20])
	 * @param username
	 * @return
	 */
	public static boolean isUsername(String username){
		if(username.length()<3 || username.length()>20)return false;
		String regEx="^[A-Za-z0-9_]+$";
	    Pattern p=Pattern.compile(regEx);
	    Matcher m=p.matcher(username);
	    return m.matches();
	}

	/**
	 * @Author haien
	 * @Description 是否邮箱
	 * @Date 2019/2/3
	 * @Param [email]
	 * @return boolean
	 **/
	public static boolean isEmail(String email){
		final String regEx="^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pat = Pattern.compile(regEx);
		boolean flag = false;
		Matcher matcher = pat.matcher(email);
		flag = matcher.matches();
		
		return flag;
	}
}
