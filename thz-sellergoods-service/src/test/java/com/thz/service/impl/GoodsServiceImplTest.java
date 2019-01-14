package com.thz.service.impl;

import com.github.pagehelper.PageInfo;
import com.thz.pojo.Goods;
import com.thz.service.GoodsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
public class GoodsServiceImplTest {
    @Resource
    private GoodsService goodsService;

    @Test
    public void searchByType(){
        Map<String,Object> params=new HashMap<>();
        //params.put("number",1L);
        params.put("reportDateTime","2017/10/02 13:11/11");
        //params.put("name","食用油");
        //params.put("type","液体");
        Goods goods=goodsService.searchByType(params);
    }

    @Test
    public void fuzzySearch(){
        String keyWord="液体"; //2018/12/02 10:20:04
        List<Goods> goodsList=goodsService.fuzzySearch(0,5,keyWord);
        PageInfo<Goods> goodsPageInfo=new PageInfo<>(goodsList);
        for(Goods goods:goodsList){
            System.out.println(goods.getName());
        }
        Assert.assertThat(goodsPageInfo.getTotal(),is(3L));
    }

}