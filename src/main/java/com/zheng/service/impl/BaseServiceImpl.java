package com.zheng.service.impl;

import com.google.common.collect.Lists;
import com.zheng.service.BaseService;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenglian on 2016/9/24.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract CrudRepository<T, Serializable> getRepository();

    @Override
    public T get(Serializable id) {
        System.out.println("从数据库中获取" + id + "对应的用户......");
        return getRepository().findOne(id);
    }

    @Override
    public void update(T t) {
        getRepository().save(t);
    }

    @Override
    public void save(T t) {
        getRepository().save(t);
    }

    @Override
    public void delete(Serializable id) {
        getRepository().delete(id);
    }

    @Override
    public List<T> findAll() {
        return Lists.newArrayList(getRepository().findAll());
    }
}
