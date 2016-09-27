package com.zheng.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * Created by zhenglian on 2016/9/26.
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if(request.getAttribute(getFailureKeyAttribute()) != null) { //如果验证已经失败，就不走下面的用户认证
            return true; //这里需要返回true跳转到loginController进行处理
        }
		return super.onAccessDenied(request, response);
	}
}
