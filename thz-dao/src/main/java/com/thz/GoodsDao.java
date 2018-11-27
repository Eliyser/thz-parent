package com.thz;

import java.util.List;

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
     * @return com.thz.Goods
     **/
    public List<Goods> fuzzySearch(String keyWord);

    /**
     * @Author haien
     * @Description 分类查询
     * @Date 2018/11/26
     * @Param [goods]
     * @return java.util.List<com.thz.Goods>
     **/
    public List<Goods> searchByType(Goods goods);
}




















