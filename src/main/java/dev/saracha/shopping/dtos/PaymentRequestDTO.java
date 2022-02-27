package dev.saracha.shopping.dtos;

import dev.saracha.shopping.domains.PaymentMethod;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentRequestDTO implements Serializable {
    private PaymentMethod method;
    private BigDecimal amount;

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod paymentMethod) {
        this.method = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
