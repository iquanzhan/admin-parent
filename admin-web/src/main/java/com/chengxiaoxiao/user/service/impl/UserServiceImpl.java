package com.chengxiaoxiao.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.user.entity.User;
import com.chengxiaoxiao.user.mapper.UserMapper;
import com.chengxiaoxiao.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/5/1 3:48 下午
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
