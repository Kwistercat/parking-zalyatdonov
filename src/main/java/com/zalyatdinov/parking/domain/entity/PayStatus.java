package com.zalyatdinov.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PayStatus {
    PAID,
    NOT_PAID;

    @JsonCreator
    public static PayStatus create(final JsonNode jsonNode) {
        String value = "";
        if (jsonNode.has("payStatus")) {
            value = jsonNode.get("payStatus").asText();
        } else {
            value = jsonNode.asText();
        }
        for (PayStatus type : PayStatus.values()) {
            if (type.name().equals(value)) {
                return type;
            }
        }
        return null;
    }

}