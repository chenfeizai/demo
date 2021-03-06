package com.spring;

import com.demo.StartApplication;
import com.demo.model.simple.News;
import com.demo.service.simple.NewsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class NewsServiceTest {

    private static final Logger log = LoggerFactory.getLogger(NewsServiceTest.class);

    @Autowired
    private NewsService newsService;

    @Test
    public void addNews() {
        try {
            log.info("# 生产测试数据");
            News news = null;
            for (int i = 1; i < 1001; i++) {
                news = new News();
                news.setTitle("test_" + i);
                news.setDescription("test_" + i);
                news.setAddress("test_" + i);
                news.setNewsTime(Calendar.getInstance().getTime());
                newsService.addNews(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
