package ua.taya.tayalab_boot.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    AGENT,
    CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
