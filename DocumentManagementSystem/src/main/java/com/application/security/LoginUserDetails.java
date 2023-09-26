package com.application.security;

import com.application.entity.User;
import com.application.entity.enumeration.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class LoginUserDetails implements UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";
    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    public LoginUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new ArrayList<>();
        if (user.getRole().getName().equals(UserRole.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.ADMIN.name()));
        }
        if (user.getRole().getName().equals(UserRole.CLIENT)) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.CLIENT.name()));
        }
        if (user.getRole().getName().equals(UserRole.DIRECTOR)) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.DIRECTOR.name()));
        }
        if (user.getRole().getName().equals(UserRole.DEPARTMENT_HEAD)) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.DEPARTMENT_HEAD.name()));
        }
        if (user.getRole().getName().equals(UserRole.DEPARTMENT_SPECIALIST)) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.DEPARTMENT_SPECIALIST.name()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}