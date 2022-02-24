package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(
            value = "SELECT SUM(c.total_cost) FROM cart c " +
                    "INNER JOIN order_detail od ON c.cart_id = od.card_id " +
                    "INNER JOIN customer_order co ON od.order_id = co.order_id" +
                    "WHERE co.order_id = ? AND c.cart_status = 'FILLED'",
            nativeQuery = true
    )
    BigDecimal calculateTotalAmountOrderByOrderId(Integer orderId);
}
