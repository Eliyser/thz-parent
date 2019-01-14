package com.thz.service.impl;

import com.github.pagehelper.PageHelper;
import com.thz.dao.GoodsDao;
import com.thz.pojo.Goods;
import com.thz.service.GoodsService;
import com.thz.tool.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Override
    public List<Goods> fuzzySearch(int pageNum,int size,String keyWord) {
        //物理分页：设置起始页码和容量
        PageHelper.startPage(pageNum,size);
        //如果关键词是时间则查找最近的记录
        if(StringUtil.isTime(keyWord)){
            return goodsDao.findAllOrderByTime(keyWord);
        }
        //否则按类型、次数、名称来查找
        return goodsDao.fuzzySearch(keyWord);
    }

    /**
     * @Author haien
     * @Description 分类查询
     * @Date 2018/11/30
     * @Param [pageNum, size, params]
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    @Override
    public Goods searchByType(Map<String,Object> params){
        return goodsDao.searchByType(params);
    }

    /**
     * @Author haien
     * @Description 查询所有物质，按报告时间从近到远排序
     * @Date 2018/11/30
     * @Param []
     * @return java.util.List<com.thz.pojo.Goods>
     **/
    @Override
    public List<Goods> findAll(int pageNum,int size){
        //物理分页：设置起始页码和容量
        PageHelper.startPage(pageNum,size);
        return goodsDao.findAllOrderByTime(null);
    }

    @Override
    public List<Goods> findByType(int pageNum,int size,String type) {
        PageHelper.startPage(pageNum,size);
        return goodsDao.findByType(type);
    }
}
