package com.chengxiaoxiao.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.web.dtos.UserModelDto;
import com.chengxiaoxiao.model.web.pojos.User;
import com.chengxiaoxiao.model.repository.UserRepository;
import com.chengxiaoxiao.web.exception.GlobleException;
import com.chengxiaoxiao.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public User insert(UserModelDto userDto) {
        User user = new User();

        BeanUtil.copyProperties(user, userDto);

        user.setId(idWorker.nextId() + "");
        user.setLocked(0);
        user.setDeleteStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        save(user);
        return user;
    }

    @Override
    public User update(String id, UserModelDto userDto) {
        User userDb = find(id);
        if (userDb == null) {
            throw new GlobleException(CodeMsg.USER_NOT_EXIST);
        }

        BeanUtil.copyProperties(userDb, userDto);

        userDb.setUpdateTime(new Date());
        save(userDb);
        return userDb;
    }
}
