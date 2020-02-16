package com.chengxiaoxiao.web.service;

import com.chengxiaoxiao.model.web.dtos.SysResourceModelDto;
import com.chengxiaoxiao.model.web.dtos.SysResourceSearchDto;
import com.chengxiaoxiao.model.web.dtos.SysResourceModelDto;
import com.chengxiaoxiao.model.web.dtos.SysResourceSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
}
