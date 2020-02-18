package com.chengxiaoxiao.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chengxiaoxiao.common.utils.CastEntityUtil;
import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.mappers.web.SysRoleMapper;
import com.chengxiaoxiao.model.mappers.web.SysUserMapper;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.repository.SysRoleRepository;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.web.exception.GlobleException;
import com.chengxiaoxiao.web.service.SysRoleService;
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
import java.util.Collections;
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
@SuppressWarnings("all")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CastEntityUtil castEntityUtil;


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

    @Override
    public List<SysRoleSimpleDtos> getRolesByUserId(String id) {
        return sysRoleMapper.getRolesByUserId(id);
    }

    @Override
    public SysRoleTreeDto treeRolesByParentId(String parentId) {
        SysRoleTreeDto sysRoleTreeDto;
        if (parentId.equalsIgnoreCase("0")) {
            sysRoleTreeDto = new SysRoleTreeDto();
            sysRoleTreeDto.setId("0");
            sysRoleTreeDto.setName("根节点");
            sysRoleTreeDto.setDescript("根节点不可进行编辑");
        } else {
            sysRoleTreeDto = sysRoleMapper.getRoleById(parentId);
        }
        sysRoleTreeDto.setChildren(getRolesByParentId(parentId));

        return sysRoleTreeDto;
    }

    /**
     * 根据父ID递归调用角色信息
     *
     * @param roleId
     * @return
     */
    private List<SysRoleTreeDto> getRolesByParentId(String roleId) {
        //获取parentId获取角色信息其子元素
        List<SysRoleTreeDto> childrenRole = sysRoleMapper.getRolesByParentId(roleId);
        for (int i = 0; i < childrenRole.size(); i++) {
            childrenRole.get(i).setChildren(getRolesByParentId(childrenRole.get(i).getId()));
        }
        return childrenRole;
    }
}
