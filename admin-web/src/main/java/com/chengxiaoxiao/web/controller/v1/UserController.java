package com.chengxiaoxiao.web.controller.v1;

import com.chengxiaoxiao.api.user.UserControllerApi;
import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.UserSearchDto;
import com.chengxiaoxiao.model.web.pojos.User;
import com.chengxiaoxiao.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:48 下午
 * @Description:
 */
@RestController
@RequestMapping("/v1/user")
public class UserController implements UserControllerApi {
    @Autowired
    UserService userService;

    @Override
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(userService.findById(id));
    }

    @Override
    @GetMapping("/")
    public Result search(UserSearchDto userSearchDto, PageQueryDtos pageQueryDtos) {
        return Result.success(null);
    }

    @Override
    @PostMapping("/")
    public Result insert(@RequestBody User user) {
        return Result.success(user);
    }

    @Override
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody User user) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(null);
    }
}
