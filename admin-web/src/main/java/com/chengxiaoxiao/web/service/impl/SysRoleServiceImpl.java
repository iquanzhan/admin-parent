package com.chengxiaoxiao.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.repository.SysRoleRepository;
import com.chengxiaoxiao.model.web.dtos.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.web.exception.GlobleException;
import com.chengxiaoxiao.web.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * 角色信息处理
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:06 下午
 * @Description:
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private IdWorker idWorker;

    @Override
    public BaseDao<SysRole, String> getBaseDao() {
        return this.sysRoleRepository;
    }

    @Override
    public Page search(SysRoleSearchDto sysRoleSearchDto, PageRequest pageRequest) {
        return getBaseDao().findAll(new Specification<SysRole>() {
            @Override
            public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isBlank(sysRoleSearchDto.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + sysRoleSearchDto.getName() + "%");
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(sysRoleSearchDto.getRoleKey())) {
                    Predicate predicate = criteriaBuilder.like(root.get("roleKey").as(String.class), "%" + sysRoleSearchDto.getRoleKey() + "%");
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(sysRoleSearchDto.getDescript())) {
                    Predicate predicate = criteriaBuilder.like(root.get("descript").as(String.class), "%" + sysRoleSearchDto.getDescript() + "%");
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(sysRoleSearchDto.getParentId())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("parentId").as(String.class), sysRoleSearchDto.getDescript());
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(sysRoleSearchDto.getCreateUser())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("createUser").as(String.class), sysRoleSearchDto.getCreateUser());
                    list.add(predicate);
                }

                if (sysRoleSearchDto.getStartCreateTime() != null) {
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), sysRoleSearchDto.getStartCreateTime());
                    list.add(predicate);
                }
                if (sysRoleSearchDto.getEndCreateTime() != null) {
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), sysRoleSearchDto.getEndCreateTime());
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
    public SysRole insert(SysRoleModelDto sysRoleDto) {
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(sysRoleDto, sysRole, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        sysRole.setId(idWorker.nextId() + "");
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(new Date());
        sysRole.setDeleteStatus(0);
        save(sysRole);
        return sysRole;
    }

    @Override
    public SysRole update(String id, SysRoleModelDto sysRoleDto) {
        SysRole sysRoleDb = find(id);
        if (sysRoleDb == null) {
            throw new GlobleException(CodeMsg.ROLE_NOT_EXIST);
        }

        BeanUtil.copyProperties(sysRoleDto, sysRoleDb, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

        sysRoleDb.setUpdateTime(new Date());
        save(sysRoleDb);
        return sysRoleDb;
    }
}
