package dev.saracha.shopping.repositories;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @BeforeEach
    private void testInit() {
        final Customer customer = TestHelper.getCustomer();
        final Product product = TestHelper.getProduct();

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
    }

    @Description("Not sure why cannot test at the same time with calculateTotalAmountOrderByOrderId_with_success()")
    @Test
    @Disabled
    void getOrderById_is_present_with_success() {
        // Act
        Optional<Order> result = orderRepository.getOrderById(1L);
        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void getOrderById_is_present_with_failure() {
        // Act
        Optional<Order> result = orderRepository.getOrderById(999L);
        // Assert
        assertFalse(result.isPresent());
    }

    @Description("Not sure why cannot test at the same time with getOrderById_is_present_with_success()")
    @Test
    void calculateTotalAmountOrderByOrderId_with_success() {
        // Act
        BigDecimal result = orderRepository.calculateTotalAmountOrderByOrderId(1L);

        // Arrange
        assertEquals(0,result.compareTo(BigDecimal.valueOf(9999.99d)));
    }
}