package com.chengxiaoxiao.model.mappers.web;

import com.chengxiaoxiao.model.web.dtos.result.SysResourceTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysUser;

import java.util.List;

/**
 * SysResourceMapper
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/18 4:09 下午
 * @Description:
 */
public interface SysResourceMapper {

    /**
     * 根据Id查询资源信息
     *
     * @param id
     * @return
     */
    SysResourceTreeDto getResourceById(String id);

    /**
     * 根据父Id查询资源信息
     *
     * @param parentId
     * @return
     */
    List<SysResourceTreeDto> getResourceByParentId(String parentId);
}
