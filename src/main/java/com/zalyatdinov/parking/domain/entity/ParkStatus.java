package com.zalyatdinov.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ParkStatus {
    FREE,
    BUSY;

    @JsonCreator
    public static ParkStatus create(final JsonNode jsonNode) {
        String value = "";
        if (jsonNode.has("parkStatus")) {
            value = jsonNode.get("parkStatus").asText();
        } else {
            value = jsonNode.asText();
        }
        for (ParkStatus type : ParkStatus.values()) {
            if (type.name().equals(value)) {
                return type;
            }
        }
        return null;
    }

}
