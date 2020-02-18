package com.chengxiaoxiao.model.mappers.web;

import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.pojos.SysUser;

import java.util.List;

/**
 * SysRoleMapper
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/18 4:09 下午
 * @Description:
 */
public interface SysRoleMapper {
    /**
     * 根据用户Id获取所属角色信息
     * @param id
     * @return
     */
    List<SysRoleSimpleDtos> getRolesByUserId(String id);
}
