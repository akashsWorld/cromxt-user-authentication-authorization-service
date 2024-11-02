package com.cromxt.user.entity;

import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TODO remove the annotation

public enum Role {
//    TODO: Implement the permissions for each role.
    ADMIN(Set.of()),
    MANAGER(Set.of()),
    USER(Set.of());

    private final Set<Permission> permissions;

    private Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toList());

    }
}
