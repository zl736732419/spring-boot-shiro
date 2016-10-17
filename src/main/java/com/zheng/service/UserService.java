package com.zheng.service;

import java.util.List;

import com.zheng.domain.User;

/**
 * Created by zhenglian on 2016/9/24.
 */
public interface UserService extends BaseService<User> {
    /**
     * 通过username查找用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);
}
