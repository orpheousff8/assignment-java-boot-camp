package dev.saracha.shopping.services;

import dev.saracha.shopping.domains.Order;
import dev.saracha.shopping.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public BigDecimal calculateTotalAmountOrderByOrderId(Long orderId) {
        return orderRepository.calculateTotalAmountOrderByOrderId(orderId);
    }
}
