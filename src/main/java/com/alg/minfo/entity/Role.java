package com.alg.minfo.entity;


import java.security.Permission;
import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.WEATHER_READ, Permissions.WEATHER_WRITE , Permissions.WEATHER_DELETE)),
    USER(Set.of(Permissions.WEATHER_READ));

    private final Set<Permissions> permissions;

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}
