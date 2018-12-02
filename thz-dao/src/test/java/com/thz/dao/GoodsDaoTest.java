package com.thz.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
public class GoodsDaoTest {
    @Resource
    private GoodsDao goodsDao;

    @Test
    public void searchByType() {
        /*Goods goods=new Goods();
        goods.setNumber(1L);
        List<Goods> goodsList=goodsDao.searchByType(goods);
        Assert.assertThat(goodsList.get(0).getId(),is(1L));*/
    }
}