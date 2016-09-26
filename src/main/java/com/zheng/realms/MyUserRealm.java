package com.zheng.realms;

import com.google.common.collect.Sets;
import com.zheng.domain.Permission;
import com.zheng.domain.Role;
import com.zheng.domain.User;
import com.zheng.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by zhenglian on 2016/9/25.
 */
public class MyUserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("权限认证=====> doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = (String) pc.getPrimaryPrincipal();
        User user = userService.findByUsername(username);
        if(user == null) {
            return null;
        }

        List<Role> roles = user.getRoles();
        Set<String> roleNames = Sets.newHashSet();
        Set<String> permissions = Sets.newHashSet();

        for(Role role : roles) {
            roleNames.add(role.getRole());
            for(Permission permission : role.getPermissions()) {
                permissions.add(permission.getPermission());
            }
        }
        
        System.out.println("角色列表=============:" + roleNames);
        System.out.println("权限列表=============:" + permissions);
        
        info.setRoles(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("身份认证==========>doGetAuthenticationInfo");
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("登录用户不存在");
        }

        if (user.getState() == 2) {
            throw new LockedAccountException("当前用于已经被锁定!");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialSalt()), getName());

        return info;
    }
}
