package com.zheng.web;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhenglian on 2016/9/25.
 */
public abstract class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @ModelAttribute
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @ModelAttribute
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    protected void putRequestContext(String key, Object value) {
        this.request.setAttribute(key, value);
    }

    protected void putSessionContext(String key, Object value) {
        this.request.getSession().setAttribute(key, value);
    }

    protected HttpSession getSession() {
        return request.getSession();
    }

}
