package dev.saracha.shopping.repositories;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    private final Customer customer = TestHelper.getCustomer();
    private final Product product = TestHelper.getProduct();

    @Test
    void getOrderDetailListByOrderId_is_not_empty_with_success() {
        // Arrange
        customerRepository.save(customer);
        productRepository.save(product);

        Cart cart = new Cart();
        // id is automatically set
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setOrderQuantity(1);
        cart.setTotalCost(product.getPrice());
        cart.setStatus(CartStatus.FILLED);
        cartRepository.save(cart);

        Order order = new Order();
        order.setCustomer(customer);
        orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCart(cart);
        orderDetail.setOrder(order);
        orderDetailRepository.save(orderDetail);

        // Act
        List<OrderDetail> result = orderDetailRepository.getOrderDetailListByOrderId(1L);

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    void getOrderDetailListByOrderId_is_not_empty_with_failure() {
        // Act
        List<OrderDetail> result = orderDetailRepository.getOrderDetailListByOrderId(1L);

        // Assert
        assertTrue(result.isEmpty());
    }
}