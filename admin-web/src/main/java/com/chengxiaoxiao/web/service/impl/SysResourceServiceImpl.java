package com.chengxiaoxiao.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.repository.SysResourceRepository;
import com.chengxiaoxiao.model.web.dtos.SysResourceModelDto;
import com.chengxiaoxiao.model.web.dtos.SysResourceSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.web.exception.GlobleException;
import com.chengxiaoxiao.web.service.SysResourceService;
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
 * 资源信息处理
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/16 10:06 下午
 * @Description:
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource, String> implements SysResourceService {
    @Autowired
    private SysResourceRepository sysResourceRepository;
    @Autowired
    private IdWorker idWorker;

    @Override
    public BaseDao<SysResource, String> getBaseDao() {
        return this.sysResourceRepository;
    }

    @Override
    public Page search(SysResourceSearchDto sysResourceSearchDto, PageRequest pageRequest) {
        return getBaseDao().findAll(new Specification<SysResource>() {
            @Override
            public Predicate toPredicate(Root<SysResource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isBlank(sysResourceSearchDto.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + sysResourceSearchDto.getName() + "%");
                    list.add(predicate);
                }
                if (sysResourceSearchDto.getType()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("type").as(String.class), sysResourceSearchDto.getType());
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(sysResourceSearchDto.getSourceUrl())) {
                    Predicate predicate = criteriaBuilder.like(root.get("scourceKey").as(String.class), "%" + sysResourceSearchDto.getSourceUrl() + "%");
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(sysResourceSearchDto.getSourceUrl())) {
                    Predicate predicate = criteriaBuilder.like(root.get("sourceUrl").as(String.class), "%" +sysResourceSearchDto.getSourceUrl()+"");
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(sysResourceSearchDto.getDescript())) {
                    Predicate predicate = criteriaBuilder.like(root.get("descript").as(String.class), "%" +sysResourceSearchDto.getDescript()+"%");
                    list.add(predicate);
                }

                if (sysResourceSearchDto.getIsShow()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("isShow").as(String.class), sysResourceSearchDto.getIsShow());
                    list.add(predicate);
                }

                if (sysResourceSearchDto.getParentId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("parentId").as(String.class), sysResourceSearchDto.getParentId());
                    list.add(predicate);
                }

                if (sysResourceSearchDto.getCreateUser()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("createUser").as(String.class), sysResourceSearchDto.getCreateUser());
                    list.add(predicate);
                }


                if (sysResourceSearchDto.getStartCreateTime() != null) {
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), sysResourceSearchDto.getStartCreateTime());
                    list.add(predicate);
                }
                if (sysResourceSearchDto.getEndCreateTime() != null) {
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), sysResourceSearchDto.getEndCreateTime());
                    list.add(predicate);
                }

                list.add(criteriaBuilder.equal(root.get("deleteStatus").as(Integer.class), 0));

                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageRequest);
    }

    @Override
    public SysResource insert(SysResourceModelDto sysResourceDto) {
        SysResource sysResource = new SysResource();
        BeanUtil.copyProperties(sysResourceDto, sysResource, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        sysResource.setId(idWorker.nextId() + "");
        sysResource.setCreateTime(new Date());
        sysResource.setUpdateTime(new Date());
        sysResource.setDeleteStatus(0);
        save(sysResource);
        return sysResource;
    }

    @Override
    public SysResource update(String id, SysResourceModelDto sysResourceDto) {
        SysResource sysResourceDb = find(id);
        if (sysResourceDb == null) {
            throw new GlobleException(CodeMsg.ROLE_NOT_EXIST);
        }

        BeanUtil.copyProperties(sysResourceDto, sysResourceDb, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

        sysResourceDb.setUpdateTime(new Date());
        save(sysResourceDb);
        return sysResourceDb;
    }
}
