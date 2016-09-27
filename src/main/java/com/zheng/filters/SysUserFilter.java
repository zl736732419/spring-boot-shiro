package com.zheng.filters;

import com.zheng.domain.User;
import com.zheng.service.UserService;
import com.zheng.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 这里获取并保存登录用户信息
 * Created by zhenglian
 * 2016/9/27 11:35
 */
public class SysUserFilter extends PathMatchingFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest servletRequest, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUsername(username);
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        request.getSession().setAttribute(Constants.CURRENT_USER, user);
        return super.onPreHandle(request, response, mappedValue);
    }
}
