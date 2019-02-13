package com.thz.tool;

public class RandomValidateCodeUtilTest {
    public static void main(String[] args){
        String phone="19927458958";
        String code=RandomValidateCodeUtil.getVerifyCode();
        String result=null;
        /*try {
            result=SmsUtil.sendCode(phone,code);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("验证码："+code);
        System.out.println("发送结果："+result);
    }
}