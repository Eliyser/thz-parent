package com.thz.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.thz.pojo.Goods;
import com.thz.service.GoodsService;
import com.thz.tool.ServletUtil;
import com.thz.tool.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    //日志
    public static final Logger logger=LoggerFactory.getLogger(GoodsController.class);

    /**
     * @Author haien
     * @Description 模糊查询与类型遍历
     * @Date 2019/1/14
     * @Param [request, response]
     * @return void
     **/
    @RequestMapping(value="/query",method = RequestMethod.GET)
    public void queryGoods(HttpServletRequest request,HttpServletResponse response){
        //模糊查询标志
        String fuzzy=request.getParameter("fuzzy");
        //页码
        String pageNum=request.getParameter("pageNum");
        //每页行数
        String rows=request.getParameter("rows");

        //参数不完整
        if(null==pageNum||null==rows){
            //被GlobalExceptionHandler捕获处理并生成错误报文
            throw new InvalidParameterException("参数异常，请重新发送请求！");
        }

        //每页行数
        int size=Integer.parseInt(rows);
        //当前页码
        int currentPage=Integer.parseInt(pageNum)-1;
        List<Goods> goodsList=null;
        PageInfo<Goods> goodsPageInfo=null;

        //模糊查询
        if(!StringUtil.isNull(fuzzy)){ //没有这个参数则fuzzy为null值
            //搜索关键词
            String keyWord=request.getParameter("keyWord");
            //空搜
            if(StringUtil.isNull(keyWord)){
                logger.info("空搜--默认查询最近检测的所有物质");
                goodsList=goodsService.findAll(currentPage,size);
            }
            else{
                logger.info("模糊查询--关键词："+keyWord);
                goodsList=goodsService.fuzzySearch(currentPage,size,keyWord);
            }
        }
        //类型遍历
        else{
            String type=request.getParameter("type");
            goodsList=goodsService.findByType(currentPage,size,type);
        }

        goodsPageInfo=new PageInfo<>(goodsList);
        JSONObject result=new JSONObject();
        //结果集合
        result.put("goodsPage",goodsPageInfo);
        //总页数
        result.put("totalPages",goodsPageInfo.getPages());
        //总记录数
        result.put("totalRecords",goodsPageInfo.getTotal());
        //返回结果
        ServletUtil.createSuccessResponse(200,result,response);
    }

    /**
     * @Author haien
     * @Description 分类查询
     * @Date 2019/1/14
     * @Param [request, response]
     * @return void
     **/
    @RequestMapping(value="/query/bytype",method = RequestMethod.GET)
    public void queryGoodsByType(HttpServletRequest request,HttpServletResponse response){
        //时间
        String reportDateTime = request.getParameter("reportDateTime");
        //次数
        String number = request.getParameter("number");
        //物质名
        String name = request.getParameter("name");

        logger.info("分类查询--关键词：name=" + name
                + "time=" + reportDateTime + "number=" + number);

        //参数异常
        if (StringUtil.isNull(reportDateTime) || StringUtil.isNull(number) || StringUtil.isNull(name)) {
            throw new InvalidParameterException("存在空白参数，请重新发送请求！");
        }

        Map<String,Object> params=new HashMap<>();
        params.put("name", name);
        params.put("reportDateTime", reportDateTime);
        params.put("number", number);
        Goods goods = goodsService.searchByType(params);
        JSONObject result=new JSONObject();
        result.put("goods",goods);
        ServletUtil.createSuccessResponse(200,result,response);
    }
}



















