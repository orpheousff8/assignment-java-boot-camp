package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            value = "SELECT Cast(Sum(c.cart_total_cost) AS DECIMAL(10, 2)) FROM cart c " +
                    "INNER JOIN order_detail od ON c.cart_id = od.cart_id " +
                    "INNER JOIN customer_order o ON od.order_id = o.order_id " +
                    "WHERE o.order_id = ? AND c.cart_status = 'FILLED'",
            nativeQuery = true
    )
    BigDecimal calculateTotalAmountOrderByOrderId(Long orderId);

    Optional<Order> getOrderById(Long id);
}
