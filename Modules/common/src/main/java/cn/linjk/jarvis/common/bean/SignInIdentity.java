package cn.linjk.jarvis.common.bean;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SignInIdentity
 * @author: linjk
 * @date: 2022/4/11 下午9:27
 * @description: 登录认证对象
 */

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class SignInIdentity implements UserDetails {
    private static final long serialVersionUID = -3816824197261635684L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 设备名
     */
    private String deviceName;
    /**
     * 昵称
     */
    private String aliasName;
    /**
     * 密码
     */
    private String secretKey;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 角色
     */
    private String roles;
    /**
     * 是否有效 0=无效 1=有效
     */
    private int valid;
    /**
     * 是否启用 0=否 1=是
     */
    private int enableFlag;
    /**
     * 角色集合, 不能为空
     */
    private List<GrantedAuthority> authorities;

    /**
     * 获取角色信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (StrUtil.isNotBlank(this.roles)) {
            // TODO: 获取数据库中的角色信息
            this.authorities = Stream.of(this.roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        else {
            this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        }
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.secretKey;
    }

    @Override
    public String getUsername() {
        return this.deviceName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.valid!=0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enableFlag!=0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enableFlag != 0;
    }
}
