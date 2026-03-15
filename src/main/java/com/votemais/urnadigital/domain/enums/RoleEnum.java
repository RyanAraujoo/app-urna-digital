package com.votemais.urnadigital.domain.enums;

public enum RoleEnum {

    ADMIN("Admin"),
    USER("User");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}