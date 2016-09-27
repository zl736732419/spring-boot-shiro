package com.zheng.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 限制登录失败次数
 * Created by zhenglian on 2016/9/27.
 */
public class RetryLimitedHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitedHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }

        if(retryCount.incrementAndGet() > 3) {
            throw new ExcessiveAttemptsException("输入密码错误次数过多，请10分钟后再试!");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            passwordRetryCache.remove(username);
        }

        return matches;
    }
}
