package com.thz.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author haien
 * @Description 响应报文类
 * @Date 2018/12/1
 **/
public class ServletUtil {
    //服务器标识
    private static String hostName="";
    //响应的ContentType
    private static final String RESPONSE_CONTENTTYPE="application/json";
    //响应编码
    private static final String RESPONSE_CHARACTERENCODING="utf-8";
    //业务名称的缩写
    private static final String BIZ_NAME="";

    private static final Logger logger=LoggerFactory.getLogger(ServletUtil.class);

    static{
        try{
            InetAddress inetAddress=InetAddress.getLocalHost();
            hostName=inetAddress.getHostName();
        }catch (UnknownHostException e){
            logger.error("Get host name failed", e);
        }
    }

    /**
     * @Author haien
     * @Description 生成成功报文
     * @Date 2018/12/1
     * @Param [httpCode状态码, result:JSONObject, serializerFeature序列化属性
     *          , filter序列化属性, response]
     * @return java.lang.String
     **/
    public static String createSuccessResponse(Integer httpCode, Object result,
                                               SerializerFeature serializerFeature,
                                               SerializeFilter filter,
                                               HttpServletResponse response) {

        response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
        response.setContentType(RESPONSE_CONTENTTYPE);
        response.setStatus(httpCode);

        PrintWriter printWriter=null;
        String jsonString="";
        try { //必须捕获，不能抛出，因为要保证资源被关闭
            printWriter=response.getWriter();
            //如果result为空，那么客户端打印空白；不要抛出异常，因为抛出了就需要生成错误报文，不要为这种几乎不可能的异常浪费controller的代码
            if(null!=result) {
                //是否定制序列化
                if (null != filter)
                    jsonString = JSONObject.toJSONString(result, filter, serializerFeature);
                    //不定制序列化的话指定一下时间格式
                else {
                    jsonString = JSONObject.toJSONStringWithDateFormat(result,
                            "yyyy-MM-dd HH:ss:mm", serializerFeature);
                }
            }
            printWriter.write(jsonString);
            printWriter.flush();
        } catch (IOException e) {
            logger.error("Get response writer failed!",e); //不用再抛出，因为抛出了就要再生成一次报文，但既然getWrite都出错了生成的报文又怎么打印到前端呢
        }finally {
            if(null!=printWriter)
                printWriter.close();
        }

        return jsonString;
    }

    public static String createSuccessResponse(Integer httpCode,Object result,
                                               HttpServletResponse response) {
        return createSuccessResponse(httpCode,result,SerializerFeature.WriteMapNullValue,
                null,response); //输出值为null的字段
    }

    public static String createSuccessResponse(Integer httpCode,Object result,SerializeFilter filter,
                                               HttpServletResponse response) {
        return createSuccessResponse(httpCode,result,SerializerFeature.PrettyFormat,
                filter,response); //输出的时候每对键值对占一行，否则全部挤成一行
    }

    /**
     * @Author haien
     * @Description 生成错误报文
     * @Date 2018/12/1
     * @Param [httpStatus状态码, resCode应该也是状态码, code应该是请求网址, message错误信息, response]
     * @return java.lang.String
     **/
    public static String createErrorResponse(Integer httpStatus,Integer resCode,Object code,
                                             String message,HttpServletResponse response) {

        response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
        response.setContentType(RESPONSE_CONTENTTYPE);
        response.setStatus(httpStatus);
        code=BIZ_NAME+code; //不过这个BIZ_NAME不知道怎么生成的

        Map<String,Object> map=new HashMap<>();
        map.put("code",code); //不要管这些参数是否为空，空的话客户端显示很多空信息也没事，但处理成抛异常的话又要生成一遍错误报文
        map.put("message",message);
        map.put("res_code",resCode);
        map.put("server_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        String jsonString="";
        //不写出到客户端也会自动写出去的
        PrintWriter printWriter=null;
        try {
            printWriter=response.getWriter();
            jsonString=JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
            printWriter.write(jsonString);
            printWriter.flush();
        } catch (IOException e) {
            logger.error("Get response writer failed!",e);
        } finally {
            if(null!=printWriter){
                printWriter.close();
            }
        }
        return jsonString; //会自动把json返回客户端显示
    }
}





















