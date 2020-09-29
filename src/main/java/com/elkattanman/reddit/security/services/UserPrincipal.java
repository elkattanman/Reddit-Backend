package com.elkattanman.reddit.security.services;

import com.elkattanman.reddit.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> list=new HashSet<>();
        user.getRoles().forEach(role -> {
            GrantedAuthority authority= new SimpleGrantedAuthority(role.getName().name());
            list.add(authority);
            role.getPrivileges().forEach(privilege -> {
                GrantedAuthority auth= new SimpleGrantedAuthority(privilege.getName().name());
                list.add(auth);
            });
        });
        return list;
    }

    public Long getId() {
        return user.getUserId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getName() { return  user.getName(); }

    public byte[] getImg() { return user.getImg(); }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isEnabled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(getId(), user.getId());
    }
}
