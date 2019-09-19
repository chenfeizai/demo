package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String home() {
        log.info("# 进入默认首页");
        return "index";
    }

    @RequestMapping(value = "leftnav", method = RequestMethod.GET)
    public String leftnav() {
        return "leftnav";
    }

    @RequestMapping(value = "topnav", method = RequestMethod.GET)
    public String topnav() {
        return "topnav";
    }

    @RequestMapping(value = {"/403", "/error"})
    public String unauthorizedRole() {
        log.info("------error-------");
        return "common/error";
    }


}
