package com.chengxiaoxiao.model.repository;

import com.chengxiaoxiao.model.web.pojos.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:39 下午
 * @Description:
 */
public interface SysUserRepository extends BaseDao<SysUser, String> {
    /**
     * 根据用户名获取用户实体
     *
     * @param userName 用户名
     * @return 用户实体
     */
    SysUser findByUserName(String userName);
}
