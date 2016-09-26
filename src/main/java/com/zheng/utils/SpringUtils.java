package com.zheng.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by zhenglian on 2016/9/26.
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext ctx = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtils.ctx == null) {
            SpringUtils.ctx = applicationContext;
        }
    }

    public static ApplicationContext getCtx() {
        return SpringUtils.ctx;
    }
}
