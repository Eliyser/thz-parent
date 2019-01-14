package com.thz.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class GoodsControllerTest {

    @Resource
    private GoodsController goodsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc=MockMvcBuilders.standaloneSetup(goodsController).build(); //缺少这一步抛异常：No mapping found for HTTP request with URI [...] in DispatcherServlet；找不到路径
    }

    @Test
    public void queryGoods() throws Exception {
        //String url="/goods/query?fuzzy=fuzzy&pageNum=1&rows=5&keyWord=液体";
        //String url="/goods/query?fuzzy=fuzzy&pageNum=1&rows=5&keyWord=2018/11/08 21:19:08";
        //String url="/goods/query?pageNum=1&rows=5&type=液体";
        String url="/goods/query/bytype?reportDateTime=2018/12/02 10:20:04&name=青铜&number=2";
        ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(url));
        MvcResult mvcResult=resultActions.andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        System.out.println("客户端获得反馈数据："+result);
        //也可以从response中获取状态码、header、cookies...
        System.out.println(mvcResult.getResponse().getStatus());
    }
}




















