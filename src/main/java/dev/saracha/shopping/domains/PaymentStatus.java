package dev.saracha.shopping.domains;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum PaymentStatus {
    UNPAID,
    PAID,
    REJECTED,
    TIMEOUT,
    UNKNOWN;

    @JsonCreator
    public static PaymentStatus getByValue(String str) {
        PaymentStatus paymentStatus = Arrays.stream(PaymentStatus.values())
                .filter(a -> a.name().equals(str)).findFirst().orElse(PaymentStatus.UNKNOWN);
        System.out.println("str: " + str);
        System.out.println("status: " + paymentStatus);
        return paymentStatus;
    }
}
