package com.chengxiaoxiao.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.repository.SysResourceRepository;
import com.chengxiaoxiao.model.repository.SysUserRoleRepository;
import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysUserRole;
import com.chengxiaoxiao.web.exception.GlobleException;
import com.chengxiaoxiao.web.service.SysResourceService;
import com.chengxiaoxiao.web.service.SysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户角色处理
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/16 10:06 下午
 * @Description:
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, String> implements SysUserRoleService {
    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Override
    public BaseDao<SysUserRole, String> getBaseDao() {
        return sysUserRoleRepository;
    }

    @Override
    public void deleteByUserId(String userId) {
        sysUserRoleRepository.deleteByUserId(userId);
    }

    @Override
    public void deleteByRoleId(String roleId) {
        sysUserRoleRepository.deleteByRoleId(roleId);
    }
}
