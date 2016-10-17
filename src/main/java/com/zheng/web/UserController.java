package com.zheng.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zheng.domain.User;
import com.zheng.service.UserService;

/**
 * Created by zhenglian on 2016/9/25.
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

    @RequiresPermissions("user:add")
    @RequestMapping("/add")
    public String addPage() {
        return "user/add";
    }

    @RequiresRoles("admin")
    @RequestMapping("/list")
    public String listPage() {
        return "user/list";
    }
    
    @RequestMapping("/data")
    @ResponseBody
    public List<User> findList() {
    	return userService.findAll();
    }
    
}

