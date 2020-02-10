package com.chengxiaoxiao.web.service;


import com.chengxiaoxiao.model.web.dtos.UserModelDto;
import com.chengxiaoxiao.model.web.pojos.User;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:44 下午
 * @Description:
 */
public interface UserService extends BaseService<User, String> {

    User insert(UserModelDto user);

    User update(String id, UserModelDto user);
}
