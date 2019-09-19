package com.demo.service.simple;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.demo.StartApplication;

/**
 * @author xwang
 * @date 2019-09-09 13:57
 */
public class ServletInitializer  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StartApplication.class);

    }
}
