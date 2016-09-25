package com.zheng.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhenglian on 2016/9/25.
 */
@RequestMapping("/user")
@Controller
public class UserController {

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
}

