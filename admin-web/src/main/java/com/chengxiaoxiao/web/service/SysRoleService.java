package com.chengxiaoxiao.web.service;

import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:05 下午
 * @Description:
 */
public interface SysRoleService extends BaseService<SysRole, String> {
    /**
     * 条件搜索
     *
     * @param search
     * @param pageRequest
     * @return
     */
    Page search(SysRoleSearchDto search, PageRequest pageRequest);

    /**
     * 增加角色
     *
     * @param sysRole
     * @return
     */
    SysRole insert(SysRoleModelDto sysRole);

    /**
     * 更新角色信息
     *
     * @param id
     * @param sysRole
     * @return
     */
    SysRole update(String id, SysRoleModelDto sysRole);

    /**
     * 根据用户Id获取角色列表
     *
     * @param id 用户Id
     * @return
     */
    List<SysRoleSimpleDtos> getRolesByUserId(String id);
}
