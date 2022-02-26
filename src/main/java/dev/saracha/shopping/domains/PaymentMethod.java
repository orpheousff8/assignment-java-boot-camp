package dev.saracha.shopping.domains;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum PaymentMethod {
    CASH,
    CARD,
    UNKNOWN;

    @JsonCreator
    public static PaymentStatus getByValue(String str) {
        return Arrays.stream(PaymentStatus.values())
                .filter(a -> a.name().equals(str)).findFirst().orElse(PaymentStatus.UNKNOWN);
    }
}
