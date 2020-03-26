package com.majia.controller;


import com.majia.entity.User;
import com.majia.service.UserService;
import com.majia.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String main() {
        User user = userService.selectUser(1);
        System.out.println("============================================");
        System.out.println(user);

        System.out.println("--------------------------------------");
        String name = redisUtil.get("name");
        System.out.println(name);


        return "index";
    }


}
