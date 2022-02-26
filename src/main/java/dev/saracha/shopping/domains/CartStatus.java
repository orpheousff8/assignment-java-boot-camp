package dev.saracha.shopping.domains;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum CartStatus {
    EMPTY,
    FILLED,
    PAID,
    UNKNOWN;

    @JsonCreator
    public static CartStatus getByValue(String str) {
        return Arrays.stream(CartStatus.values())
                .filter(a -> a.name().equals(str)).findFirst().orElse(CartStatus.UNKNOWN);
    }
}
