package com.chengxiaoxiao.web.service;

import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:05 下午
 * @Description:
 */
public interface SysResourceService extends BaseService<SysResource,String>{
    /**
     * 条件搜索
     * @param search
     * @param pageRequest
     * @return
     */
    Page search(SysResourceSearchDto search, PageRequest pageRequest);

    SysResource insert(SysResourceModelDto sysRole);

    SysResource update(String id, SysResourceModelDto sysRole);

    /**
     * 查询角色下所拥有的的资源信息
     *
     * @param roleId
     * @return
     */
    List<SysResource> findResourcesByRoleId(String roleId);

    /**
     * 给角色分配资源
     *
     * @param roleId
     * @param resourceIds
     */
    void dispatchResourceByRoleId(String roleId, String[] resourceIds);
}
