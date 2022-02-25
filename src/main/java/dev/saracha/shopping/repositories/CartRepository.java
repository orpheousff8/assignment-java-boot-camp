package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(
            value = "SELECT * from cart c " +
                    "INNER JOIN customer cus ON c.customer_id = cus.customer_id " +
                    "WHERE c.customer_id = ? AND c.cart_status = 'FILLED'",
            nativeQuery = true
    )
    List<Cart> getShoppingCartsByCustomerId(Long customerId);
}
