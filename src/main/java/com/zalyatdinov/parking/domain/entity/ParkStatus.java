package com.zalyatdinov.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum ParkStatus {
    FREE,
    BUSY;

    @JsonCreator
    public static ParkStatus fromJson(@JsonProperty("name") String name) {
        return valueOf(name);
    }


}
