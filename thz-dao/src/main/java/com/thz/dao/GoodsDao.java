package com.thz.dao;

import com.thz.pojo.Goods;

import java.util.List;
import java.util.Map;

/**
 * @Author haien
 * @Description 物质dao接口
 * @Date 2018/11/26
 **/
public interface GoodsDao {
    /**
     * @Author haien
     * @Description 模糊查询
     * @Date 2018/11/26
     * @Param [key] 关键词
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> fuzzySearch(String keyWord);

    /**
     * @Author haien
     * @Description 分类查询
     * @Date 2018/11/30
     * @Param [params]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public Goods searchByType(Map<String,Object> params);

    /**
     * @Author haien
     * @Description 查询所有记录，按时间从近到远排序
     * @Date 2018/12/2
     * @Param []
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> findAll();

    /**
     * @Author haien
     * @Description 查询所有记录，按指定时间从近到远排序
     * @Date 2018/12/2
     * @Param [reportDateTime]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> findAllOrderByTime(String reportDateTime);

    /**
     * @Author haien
     * @Description 类型遍历
     * @Date 2019/1/14
     * @Param [pageNum, size, type]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> findByType(String type);
}




















