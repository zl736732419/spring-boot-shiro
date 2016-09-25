package com.zheng.repository;

import com.zheng.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

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
