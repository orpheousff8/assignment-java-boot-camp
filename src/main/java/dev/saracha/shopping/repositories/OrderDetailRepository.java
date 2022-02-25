package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(
            value = "SELECT * FROM order_detail od " +
                    "INNER JOIN customer_order co " +
                    "ON od.order_id = co.order_id " +
                    "WHERE od.order_id = ?",
            nativeQuery = true
    )
    List<OrderDetail> getOrderDetailByOrderId(Long orderId);
}
