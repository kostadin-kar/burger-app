package com.app.burger.burgerapi.repo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public enum Role {
    ADMIN,
    USER
}
