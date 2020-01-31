package com.chengxiaoxiao.api.user;

import com.chengxiaoxiao.model.web.pojos.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户接口类
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 11:21 下午
 * @Description:
 */
@Api(value="用户管理接口",description = "用户管理接口，提供用户的增、删、改、查")
public interface UserControllerApi {
    @ApiOperation("根据Id查询用户信息")
    User findById(Integer id);
}
