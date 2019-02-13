package com.thz.tool;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author haien
 * @Description 发送短信服务工具类
 * @Date 2019/2/10
 * @Param
 * @return
 **/
public class SmsUtil {
    //第三方平台接口
    private static final String QUERY_PATH=
            "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
    //我的账号认证
    private static final String ACCOUNT_SID="78789aaee2d54938adb96d01f96dae79";
    private static final String AUTH_TOKEN="1834bbf37ebc457e8141d290127a431d";

    public static int sendCode(String phone,String code)
            throws NoSuchAlgorithmException, IOException {

        //定义该次请求
        URL url=new URL(QUERY_PATH);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true); //是否允许数据写入
        connection.setDoOutput(true); //是否允许数据输出
        connection.setConnectTimeout(5000); //链接响应时间
        connection.setReadTimeout(10000); //参数读取时间
        connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");

        //提交请求
        String timestamp=new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());//时间戳
        String sig=getMD5(ACCOUNT_SID,AUTH_TOKEN,timestamp); //签名（用于手机设备认证）
        String tamp="您的验证码为"+code
                +"，请于5分钟内正确输入，如非本人操作，请忽略此短信。"; //短信内容
        String args=getQueryArgs(ACCOUNT_SID,tamp,phone
                ,timestamp,sig,"JSON"); //请求参数拼接
        OutputStreamWriter out=new OutputStreamWriter(connection.getOutputStream()
                ,"UTF-8");
        out.write(args); //发起请求
        out.flush();

        //读取返回参数
        BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()
                ,"UTF-8")); //br：{"respCode":"00179","respDesc":"发送短信需要先认证"}
        String temp="";
        StringBuilder result=new StringBuilder();
        while((temp=br.readLine())!=null){ //其实也就一行
            result.append(temp);
        }

        //解析参数，判断成功与否
        JSONObject json=JSONObject.parseObject(result.toString()); //转为json
        String respCode=json.getString("respCode"); //获取respCode对应的值
        String successRespCode="00000"; //成功情况：respCode=00000
        if(successRespCode.equals(respCode))  return 1; //成功
        else return 0; //失败
    }

    /**
     * @Author haien
     * @Description 获得sign(签名)
     * @Date 2019/2/10
     * @Param [sid, token, timestamp]
     * @return java.lang.String
     **/
    private static String getMD5(String sid,String token,String timestamp) throws NoSuchAlgorithmException {
        StringBuilder result=new StringBuilder();
        String source=sid+token+timestamp;

        //MessageDigest:生成信息摘要，即加密
        MessageDigest digest=MessageDigest.getInstance("MD5"); //指定用MD5算法
        byte[] bytes=digest.digest(source.getBytes()); //要进行加密的信息

        for(byte b : bytes){
            String hex=Integer.toHexString(b & 0xff); //将一个整型转为十六进制
            if(hex.length()==1) result.append("0"+hex);
            else    result.append(hex);
        }
        return result.toString();
    }

    /**
     * @Author haien
     * @Description 拼接请求参数
     * @Date 2019/2/10
     * @Param [accountSid, smsContent, to, timestamp, sig, respDataType]
     * @return java.lang.String
     **/
    public static String getQueryArgs(String accountSid,String smsContent,String to,
                                      String timestamp, String sig,String respDataType){
        return "accountSid="+accountSid+"&smsContent="+smsContent+"&to="+to+"×tamp="
                +timestamp+"&timestamp="+timestamp+"&sig="+sig+"&respDataType="+respDataType; //tamp前面是乘号
    }
}






















