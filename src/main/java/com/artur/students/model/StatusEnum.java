package com.artur.students.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusEnum {
    ACTIVE("active"),
    INACTIVE("inactive");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    @JsonCreator
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
