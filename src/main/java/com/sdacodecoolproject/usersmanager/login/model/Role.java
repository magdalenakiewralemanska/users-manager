package com.sdacodecoolproject.usersmanager.login.model;

import com.sdacodecoolproject.usersmanager.login.constant.Authorities;

import java.util.List;

public enum Role {
    ROLE_USER(Authorities.USER_AUTHORITIES),
    ROLE_MODERATOR(Authorities.MODERATOR_AUTHORITIES),
    ROLE_ADMIN(Authorities.ADMIN_AUTHORITIES);

    private List<String> authorities;

    Role(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAuthorities(){
        return authorities;
    }
}
