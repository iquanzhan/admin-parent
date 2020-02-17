package com.chengxiaoxiao.web.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.web.service.BaseService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Transactional
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseDao<T, ID> getBaseDao();

    @PersistenceContext
    protected EntityManager em;

    /**
     * 批量增加修改的size
     */
    @Value("${spring.jpa.batch-operate-size}")
    private int BATCH_SIZE = 10;

    @Override
    @Transactional
    public void batchInsert(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            em.persist(list.get(i));
            if (i % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        }
        if (size % BATCH_SIZE != 0) {
            em.flush();
            em.clear();
        }
    }

    @Override
    @Transactional
    public void batchUpdate(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            em.merge(list.get(i));
            if (i % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        }
        if (size % BATCH_SIZE != 0) {
            em.flush();
            em.clear();
        }
    }

    @Override
    public T find(ID id) {
        Optional<T> entity = getBaseDao().findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public List<T> findList(ID[] ids) {
        List<ID> idList = Arrays.asList(ids);
        return getBaseDao().findAllById(idList);

    }

    @Override
    public List<T> findList(Specification<T> spec, Sort sort) {
        return getBaseDao().findAll(spec, sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getBaseDao().findAll(pageable);
    }

    @Override
    public long count() {
        return getBaseDao().count();
    }

    @Override
    public long count(Specification<T> spec) {
        return getBaseDao().count(spec);
    }

    @Override
    public boolean exists(ID id) {
        return getBaseDao().existsById(id);
    }

    @Override
    public void save(T entity) {
        getBaseDao().save(entity);
    }

    public void save(Iterable<T> entitys) {
        getBaseDao().saveAll(entitys);
    }

    @Override
    public T update(T entity) {
        return getBaseDao().saveAndFlush(entity);
    }

    @Override
    public void delete(ID id) {

        getBaseDao().deleteById(id);
    }

    @Override
    public void deleteByIds(@SuppressWarnings("unchecked") ID... ids) {
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                ID id = ids[i];
                this.delete(id);
            }
        }
    }

    @Override
    public void delete(T[] entitys) {
        List<T> tList = Arrays.asList(entitys);
        getBaseDao().deleteAll(tList);
    }

    @Override
    public void delete(Iterable<T> entitys) {
        getBaseDao().deleteAll(entitys);
    }

    @Override
    public void delete(T entity) {
        getBaseDao().delete(entity);
    }

    @Override
    public List<T> findList(Iterable<ID> ids) {
        return getBaseDao().findAllById(ids);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return getBaseDao().findAll(spec, pageable);
    }
}

