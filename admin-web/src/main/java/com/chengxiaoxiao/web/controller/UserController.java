package com.chengxiaoxiao.web.controller;

import com.chengxiaoxiao.api.user.UserControllerApi;
import com.chengxiaoxiao.model.User;
import com.chengxiaoxiao.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:48 下午
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {
    @Autowired
    UserService userService;

    @Override
    @RequestMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }
}
