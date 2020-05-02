package com.chengxiaoxiao.user.controller;

import com.chengxiaoxiao.user.entity.User;
import com.chengxiaoxiao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/5/1 3:43 下午
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.list(null);
    }

    @PostMapping
    public User instert(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") String id) {
        userService.removeById(id);
        return true;
    }


    @PutMapping("/{id}")
    public User update(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return user;
    }

}
