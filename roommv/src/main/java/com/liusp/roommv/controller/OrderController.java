package com.liusp.roommv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jackyliu on 2015/3/12.
 */
@Controller(value ="orderController")
@RequestMapping(value = "/order")
public class OrderController {
    public static  final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @RequestMapping(value = "order")
    public @ResponseBody String order(String userAndProduct){
        return  "success";
    }
}
