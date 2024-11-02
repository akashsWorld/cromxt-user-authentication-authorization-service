package com.cromxt.user.entity;

import lombok.Getter;

@Getter
public enum Permission {
//    FIXME: refactor the permissions based on use cases.
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete");
    private final String permission;

    private Permission(String permission) {
        this.permission = permission;
    }

}
