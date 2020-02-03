package com.chengxiaoxiao.web.service.impl;

import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.web.pojos.User;
import com.chengxiaoxiao.model.repository.UserRepository;
import com.chengxiaoxiao.web.exception.GlobleException;
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
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    IdWorker idWorker;

    @Override
    public BaseDao<User, String> getBaseDao() {
        return this.userRepository;
    }

    @Override
    public User insert(User user) {
        user.setId(idWorker.nextId() + "");
        save(user);
        return user;
    }

    @Override
    public User update(String id, User user) {
        User userDb = find(id);
        if (userDb == null) {
            throw new GlobleException(CodeMsg.USER_NOT_EXIST);
        }
        user.setId(id);
        save(user);
        return user;
    }
}
