package com.zalyatdinov.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum PayStatus {
    PAID,
    NOT_PAID;

    @JsonCreator
    public static PayStatus fromJson(@JsonProperty("name") String name) {
        return valueOf(name);
    }
}