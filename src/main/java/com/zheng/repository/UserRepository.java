package com.zheng.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zheng.domain.User;

/**
 * Created by zhenglian on 2016/9/24.
 */
public interface UserRepository extends CrudRepository<User, Serializable> {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);
}
