package ru.fatvinyl.votesystem.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Anton Yolgin
 */

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}