package com.thz.service;

import com.thz.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    /**
     * @Author haien
     * @Description 模糊查询
     * @Date 2018/12/2
     * @Param [pageNum, size, keyWord]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> fuzzySearch(int pageNum,int size,String keyWord);

    /**
     * @Author haien
     * @Description 分类查询
     * @Date 2018/11/30
     * @Param [pageNum, size, params]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> searchByType(int pageNum,int size,Map<String,Object> params);

    /**
     * @Author haien
     * @Description 查询所有物质，按指定时间从近到远排序
     * @Date 2018/12/2
     * @Param [pageNum, size]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    public List<Goods> findAll(int pageNum,int size);
}
