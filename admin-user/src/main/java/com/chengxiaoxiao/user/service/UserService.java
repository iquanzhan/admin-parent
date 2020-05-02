package com.chengxiaoxiao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengxiaoxiao.model.user.entity.User;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/5/1 3:48 下午
 * @Description:
 */
public interface UserService extends IService<User> {
    User selectByUsername(String username);
}
