package com.zheng.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;
import com.zheng.filters.JcaptchaValidateFilter;
import com.zheng.filters.MyFormAuthenticationFilter;
import com.zheng.realms.MyUserRealm;

/**
 * 添加shiro支持
 * Created by zhenglian on 2016/9/25.
 */
@Configuration
public class ShiroConfig {

    /**
     * 这里通过代码的方式配置访问路径拦截
     * @param manager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        
        //这里注册默认的拦截器链如authc, anon, ssl 等
        for(DefaultFilter filter : DefaultFilter.values()) {
        	bean.getFilters().put(filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));
        }
                
        bean.getFilters().put("jcaptchaValidate", jcaptchaValidateFilter());
        bean.getFilters().put("myAuthc", myFormAuthenticationFilter());

        Map<String, String> chains = Maps.newLinkedHashMap();
//      chains.put("/user/list", "authc, roles[admin]");

        // logout已经实现了
        chains.put("/logout", "logout");
        chains.put("/captcha", "anon");
        
        //添加记住我过滤器
        chains.put("/index", "user");
        chains.put("/login", "jcaptchaValidate, myAuthc");

        chains.put("/", "user");
        chains.put("/**", "user");

        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/403");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    @Bean
    public JcaptchaValidateFilter jcaptchaValidateFilter() {
        return new JcaptchaValidateFilter();
    }

    @Bean
    public MyFormAuthenticationFilter myFormAuthenticationFilter() {
        return new MyFormAuthenticationFilter();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myUserRealm()); //注入自定义realm实现
        manager.setCacheManager(ehCacheManager()); //注入ehcacheManager实现缓存机制
        manager.setRememberMeManager(cookieRememberMeManager()); //设置记住密码
        return manager;
    }

    @Bean
    public MyUserRealm myUserRealm() {
        MyUserRealm realm = new MyUserRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        return matcher;
    }

    /**
     * 开启shiro aop注解，进行权限验证
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor =
                new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return manager;
    }

    /**
     * 记住密码
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置30天失效
        cookie.setMaxAge(259200);

        return cookie;
    }

    /**
     * 设置记住我cookie管理对象
     * @return
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        return manager;
    }



}
