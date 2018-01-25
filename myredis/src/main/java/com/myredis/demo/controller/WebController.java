package com.myredis.demo.controller;
import com.myredis.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/web")
public class WebController {

    private static final Logger logger =  LoggerFactory.getLogger(WebController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("index")
    public String index(ModelMap map){
        // 测试数据库服务
        logger.info("这里是controller");
        userService.register("zs","545664586466464");
        map.put("title", "hello world");
        return "index"; // 注意，不要在最前面加上/，linux下面会出错
    }

    @RequestMapping("error")
    public String error(ModelMap map){
        throw new RuntimeException("测试异常");
    }

}