package com.chengxiaoxiao.web.service;


import com.chengxiaoxiao.model.web.dtos.SysUserModelDto;
import com.chengxiaoxiao.model.web.dtos.SysUserSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:44 下午
 * @Description:
 */
public interface SysUserService extends BaseService<SysUser, String> {

    /**
     * 增加用户信息
     *
     * @param user 传递过来的用户实体
     * @return 保存完毕的用户实体
     */
    SysUser insert(SysUserModelDto user);

    /**
     * 根据Id更新用户信息
     *
     * @param id   用户Id
     * @param user 更新的用户信息实体
     * @return 更新过后的用户最新信息
     */
    SysUser update(String id, SysUserModelDto user);

    /**
     * 条件查询用户信息
     *
     * @param sysUserSearchDto 用户信息的查询条件
     * @param pageRequest   分页、排序的查询条件
     * @return 更具查询条件查询出来的实体
     */
    Page<SysUser> search(SysUserSearchDto sysUserSearchDto, PageRequest pageRequest);

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 单个用户信息
     */
    SysUser findUserByUserName(String userName);
}
