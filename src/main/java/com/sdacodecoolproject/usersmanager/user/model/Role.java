package com.sdacodecoolproject.usersmanager.user.model;

import com.sdacodecoolproject.usersmanager.application.constant.Authorities;

import java.util.List;

public enum Role {
    ROLE_USER(Authorities.USER_AUTHORITIES),
    ROLE_MODERATOR(Authorities.MODERATOR_AUTHORITIES),
    ROLE_ADMIN(Authorities.ADMIN_AUTHORITIES);

    private final List<String> authorities;

    Role(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAuthorities(){
        return authorities;
    }
}
