package com.zheng.domain;

import com.google.common.collect.Lists;
import com.zheng.enums.UserState;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenglian on 2016/9/24.
 */
@Table(name = "t_user")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; //昵称
    private String username;
    private String password;
    private String salt;
    /*用户状态：
        0:创建未认证(没有激活，没有输入验证码等,表示当前用户刚创建，但是没有投入使用)
        1:正常状态
        2:用户被锁定
     */
    private Integer state = UserState.STATE_UNACTIVE.getState();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Role> roles = Lists.newArrayList();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public User(String username, String password, Integer state) {
        this.username = username;
        this.password = password;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer locked) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialSalt() {
        return this.username + this.salt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name).append("username", this.username).append("password", this.password)
                .append("locked", this.state).append("salt", this.salt).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return new EqualsBuilder().append(this.name, other.name).append(this.username, other.username).append(this.password, other.password)
                .append(this.state, other.state).append(this.salt, other.salt).build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.name).append(this.username).append(this.password)
                .append(this.state).append(this.salt).append(this.getCredentialSalt()).build();
    }
}
