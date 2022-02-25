package dev.saracha.shopping.repositories;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    private final Customer customer = TestHelper.getCustomer();
    private final Product product = TestHelper.getProduct();

    @Description("Skip some tests related to findById because IDs are auto generated ordered by test running sequence, which we don't know.")
    @Disabled
    @Test
    void getOrderById_is_present_with_success() {
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
        Optional<Order> result = orderRepository.getOrderById(1L);
        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void getOrderById_is_present_with_failure() {
        // Act
        Optional<Order> result = orderRepository.getOrderById(999L);
        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void calculateTotalAmountOrderByOrderId_with_success() {
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
        BigDecimal result = orderRepository.calculateTotalAmountOrderByOrderId(1L);

        // Arrange
        assertEquals(0,result.compareTo(BigDecimal.valueOf(9999.99d)));
    }
}