package dev.saracha.shopping.domains;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum OrderStatus {
    PENDING,
    SHIPPING,
    PAID,
    ERROR,
    UNKNOWN;

    @JsonCreator
    public static OrderStatus getByValue(String str) {
        return Arrays.stream(OrderStatus.values())
                .filter(a -> a.name().equals(str)).findFirst().orElse(OrderStatus.UNKNOWN);
    }
}
