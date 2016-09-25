package com.zheng.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhenglian on 2016/9/25.
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 这里接受shiro认证结果，如果失败，将会产生shiroLoginFailture参数
     * 这里统一对登录失败结果进行处理
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        String error = null;
        String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
        if(!(StringUtils.isBlank(shiroLoginFailure))) {
            if(shiroLoginFailure.equals(UnknownAccountException.class.getName())) {
                error = "用户名不存在!";
            }else if(shiroLoginFailure.equals(LockedAccountException.class.getName())) {
                error = "当前用户已被禁用!";
            }else if(shiroLoginFailure.equals(IncorrectCredentialsException.class.getName())) {
                error = "用户名/密码错误!";
            }else if(shiroLoginFailure.equals(ExcessiveAttemptsException.class.getName())) {
                error = "登录次数过于频繁，请稍后再试!";
            }else if(shiroLoginFailure.equals(ExpiredCredentialsException.class.getName())) {
                error = "密码已失效!";
            }else {
                error = "遇到错误!";
            }
        }

        putRequestContext("error", error);


        return "login";
    }
}
