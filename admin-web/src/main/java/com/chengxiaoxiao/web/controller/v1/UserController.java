package com.chengxiaoxiao.web.controller.v1;

import com.chengxiaoxiao.api.user.UserControllerApi;
import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.UserModelDto;
import com.chengxiaoxiao.model.web.dtos.UserSearchDto;
import com.chengxiaoxiao.model.web.pojos.User;
import com.chengxiaoxiao.web.controller.BaseController;
import com.chengxiaoxiao.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户相关请求接口
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:48 下午
 * @Description:
 */
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController implements UserControllerApi {
    @Autowired
    UserService userService;

    @Override
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return Result.success(userService.find(id));
    }

    @Override
    @GetMapping("/")
    public Result search(UserSearchDto userSearchDto, PageQueryDtos pageQueryDtos) {
        Page<User> search = userService.search(userSearchDto, getPageRequest());

        return Result.success(new PageResult<User>(search));
    }

    @Override
    @PostMapping("/")
    public Result insert(@Valid @RequestBody UserModelDto user) {
        return Result.success(userService.insert(user));
    }

    @Override
    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody UserModelDto user) {
        return Result.success(userService.update(id, user));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        userService.delete(id);
        return Result.success(null);
    }
}
