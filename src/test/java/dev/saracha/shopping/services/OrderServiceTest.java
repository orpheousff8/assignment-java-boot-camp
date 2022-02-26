package dev.saracha.shopping.services;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.Customer;
import dev.saracha.shopping.domains.Order;
import dev.saracha.shopping.domains.OrderStatus;
import dev.saracha.shopping.repositories.CustomerRepository;
import dev.saracha.shopping.repositories.OrderRepository;
import dev.saracha.shopping.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    private Customer customer = TestHelper.getCustomer();

    @Test
    void getOrderById() {
        // Arrange
        Order order = new Order();
        order.setCustomer(customer);
        when(orderRepository.getOrderById(1L)).thenReturn(Optional.of(order));
        // Act
        Order result = orderService.getOrderById(1L);
        // Assert
        assertEquals(order.getCustomer(), result.getCustomer());
    }

    @Test
    void updateOrder() {
        // Arrange
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        when(orderRepository.save(order)).thenReturn(order);
        // Act
        Order result = orderService.updateOrder(order);
        // Assert
        assertEquals(order.getCustomer(), result.getCustomer());
        assertEquals(OrderStatus.PENDING, result.getStatus());
    }

    @Test
    void calculateTotalAmountOrderByOrderId() {
        // Arrange
        when(orderRepository.calculateTotalAmountOrderByOrderId(1L)).thenReturn(BigDecimal.valueOf(9999.99d));
        // Act
        BigDecimal result = orderService.calculateTotalAmountOrderByOrderId(1L);
        // Assert
        assertEquals(0, BigDecimal.valueOf(9999.99d).compareTo(result));
    }
}