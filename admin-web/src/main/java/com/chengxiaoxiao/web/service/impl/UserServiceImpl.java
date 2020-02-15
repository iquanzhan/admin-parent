package com.chengxiaoxiao.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.web.dtos.UserModelDto;
import com.chengxiaoxiao.model.web.dtos.UserSearchDto;
import com.chengxiaoxiao.model.web.pojos.User;
import com.chengxiaoxiao.model.repository.UserRepository;
import com.chengxiaoxiao.web.exception.GlobleException;
import com.chengxiaoxiao.web.service.UserService;
import io.swagger.annotations.ApiModelProperty;
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
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:45 下午
 * @Description:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    IdWorker idWorker;

    @Override
    public BaseDao<User, String> getBaseDao() {
        return this.userRepository;
    }

    @Override
    public User insert(UserModelDto userDto) {
        User user = new User();

        BeanUtil.copyProperties(userDto, user);

        user.setId(idWorker.nextId() + "");
        user.setLocked(0);
        user.setDeleteStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        save(user);
        return user;
    }

    @Override
    public User update(String id, UserModelDto userDto) {
        User userDb = find(id);
        if (userDb == null) {
            throw new GlobleException(CodeMsg.USER_NOT_EXIST);
        }

        BeanUtil.copyProperties(userDto, userDb);

        userDb.setUpdateTime(new Date());
        save(userDb);
        return userDb;
    }



    @ApiModelProperty(value = "创建时间-开始时间", dataType = "date", notes = "yyyy-mm-dd")
    private Date startCreateTime;
    @ApiModelProperty(value = "创建时间-结束时间", dataType = "date", notes = "yyyy-mm-dd")
    private Date endCreateTime;

    @Override
    public Page<User> search(UserSearchDto userSearchDto, PageRequest pageRequest) {

        return getBaseDao().findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isBlank(userSearchDto.getUserName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("userName").as(String.class), "%" + userSearchDto.getUserName() + "%");
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(userSearchDto.getTelephone())) {
                    Predicate predicate = criteriaBuilder.like(root.get("telephone").as(String.class), "%" + userSearchDto.getTelephone() + "%");
                    list.add(predicate);
                }
                if (userSearchDto.getBirthdayStartTime() != null) {
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday").as(Date.class), userSearchDto.getBirthdayStartTime());
                    list.add(predicate);
                }
                if (userSearchDto.getBirthdayEndTime() != null) {
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("birthday").as(Date.class), userSearchDto.getBirthdayEndTime());
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(userSearchDto.getEmail())) {
                    Predicate predicate = criteriaBuilder.like(root.get("email").as(String.class), "%" + userSearchDto.getEmail() + "%");
                    list.add(predicate);
                }
                if (userSearchDto.getSex()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("sex").as(Integer.class),  userSearchDto.getSex());
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(userSearchDto.getAddress())) {
                    Predicate predicate = criteriaBuilder.like(root.get("address").as(String.class), "%" + userSearchDto.getAddress() + "%");
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(userSearchDto.getDescript())) {
                    Predicate predicate = criteriaBuilder.like(root.get("descript").as(String.class), "%" + userSearchDto.getDescript() + "%");
                    list.add(predicate);
                }
                if (userSearchDto.getLocked()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("locked").as(Integer.class),  userSearchDto.getLocked());
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(userSearchDto.getNickName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("nickName").as(String.class), "%" + userSearchDto.getNickName() + "%");
                    list.add(predicate);
                }

                if (userSearchDto.getDeleteStatus()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("deleteStatus").as(Integer.class),  userSearchDto.getDeleteStatus());
                    list.add(predicate);
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageRequest);
    }
}
