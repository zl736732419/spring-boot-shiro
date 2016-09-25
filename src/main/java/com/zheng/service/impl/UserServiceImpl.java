package com.zheng.service.impl;

import com.zheng.domain.User;
import com.zheng.repository.UserRepository;
import com.zheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by zhenglian on 2016/9/24.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected CrudRepository<User, Serializable> getRepository() {
        return userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
