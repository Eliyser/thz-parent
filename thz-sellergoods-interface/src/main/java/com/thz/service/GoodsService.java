package com.thz.service;

import com.thz.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    /**
     * @Author haien
     * @Description 模糊查询：种类、时间、次数、物质名，当关键词为时间时，调用findAllOrderByTime查找离该时间最近的记录
     * @Date 2018/12/2
     * @Param [pageNum, size, keyWord]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> fuzzySearch(int pageNum,int size,String keyWord);

    /**
     * @Author haien
     * @Description 分类查询：时间、次数、物质名
     * @Date 2018/11/30
     * @Param [pageNum, size, params]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public Goods searchByType(Map<String,Object> params);

    /**
     * @Author haien
     * @Description 查询所有物质，按指定时间从近到远排序
     * @Date 2018/12/2
     * @Param [pageNum, size]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> findAll(int pageNum,int size);

    /**
     * @Author haien
     * @Description 类型遍历
     * @Date 2019/1/14
     * @Param [type]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> findByType(int pageNum,int size,String type);
}
