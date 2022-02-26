package dev.saracha.shopping.domains;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @Column(name = "payment_method", length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(name = "payment_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "payment_status", length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
