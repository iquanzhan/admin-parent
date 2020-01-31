package com.chengxiaoxiao.web.service.impl;

import com.chengxiaoxiao.model.web.pojos.User;
import com.chengxiaoxiao.web.repository.UserRepository;
import com.chengxiaoxiao.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:45 下午
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
}
