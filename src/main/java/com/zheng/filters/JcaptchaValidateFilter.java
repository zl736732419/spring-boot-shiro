package com.zheng.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 定义jcaptcha验证过滤器
 * Created by zhenglian on 2016/9/26.
 */
public class JcaptchaValidateFilter extends AccessControlFilter {
    private boolean jcaptchaEnable = true; //前台是否开启验证码验证
    private String jcaptchaParam = "captcha"; //前台传递过来的验证码参数
    private String failureKeyAttribute = "shiroLoginFailure"; //shiro验证失败的参数

    @Autowired
    private ImageCaptchaService captchaService = null;

    public void setCaptchaService(ImageCaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    public void setJcaptchaEnable(boolean jcaptchaEnable) {
        this.jcaptchaEnable = jcaptchaEnable;
    }

    public void setJcaptchaParam(String jcaptchaParam) {
        this.jcaptchaParam = jcaptchaParam;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        request.setAttribute("jcaptchaEnable", this.jcaptchaEnable);

        if(!this.jcaptchaEnable || !"post".equals(request.getMethod().toLowerCase())) { //如果没有开启验证或者不是post提交就不需要验证码验证，直接跳过
            return true;
        }
        String captcha = request.getParameter(jcaptchaParam);
        boolean result = false;
        try {
			result = captchaService.validateResponseForID(request.getSession().getId(), captcha);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return result;

    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.setAttribute(failureKeyAttribute, "验证码错误!");
        return true;
    }
}
