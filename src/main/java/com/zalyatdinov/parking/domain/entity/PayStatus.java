package com.zalyatdinov.parking.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum PayStatus {
    PAID,
    NOT_PAID;
}