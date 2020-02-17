package com.chengxiaoxiao.web.service;

import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:05 下午
 * @Description:
 */
public interface SysRoleService extends BaseService<SysRole,String>{
    /**
     * 条件搜索
     * @param search
     * @param pageRequest
     * @return
     */
    Page search(SysRoleSearchDto search, PageRequest pageRequest);

    SysRole insert(SysRoleModelDto sysRole);

    SysRole update(String id, SysRoleModelDto sysRole);
}
