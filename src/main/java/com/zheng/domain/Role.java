package com.zheng.domain;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhenglian on 2016/9/25.
 */
@Table(name = "t_role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role; //shiro使用，比如admin
    private String description; //用于页面显示比如管理员

    private Integer available;
    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "t_user_role", joinColumns = {
            @JoinColumn(name = "roleId")
    }, inverseJoinColumns = {
            @JoinColumn(name = "userId")
    })
    private List<User> users = Lists.newArrayList();
    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "t_role_permission", joinColumns = {
            @JoinColumn(name = "roleId")
    }, inverseJoinColumns = {
            @JoinColumn(name = "permissionId")
    })
    private List<Permission> permissions = Lists.newArrayList();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(this.role).append(this.description)
                .append(this.available).build();
    }
}
