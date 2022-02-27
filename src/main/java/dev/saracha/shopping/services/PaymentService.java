package dev.saracha.shopping.services;

import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.CartRepository;
import dev.saracha.shopping.repositories.PaymentRepository;
import dev.saracha.shopping.dtos.PaymentRequestDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    private OrderService orderService;
    private PaymentRepository paymentRepository;
    private OrderDetailService orderDetailService;
    private CartRepository cartRepository;

    public PaymentService() {
    }

    public PaymentService(OrderService orderService, PaymentRepository paymentRepository, OrderDetailService orderDetailService, CartRepository cartRepository) {
        this.orderService = orderService;
        this.paymentRepository = paymentRepository;
        this.orderDetailService = orderDetailService;
        this.cartRepository = cartRepository;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Payment makePayment(PaymentRequestDTO paymentRequestDTO, Long orderId) {
        Order order = orderService.getOrderById(orderId);
        order.setStatus(OrderStatus.PAID);
        orderService.updateOrder(order);

        if (validatePayment(order.getId(), paymentRequestDTO)) {
            throw new IllegalArgumentException();
        }

        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailListByOrderId(order.getId());

        updateCartStatus(orderDetailList);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(paymentRequestDTO.getMethod());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PAID);

        return paymentRepository.save(payment);
    }

    private void updateCartStatus(List<OrderDetail> orderDetail) {
        for (OrderDetail detail : orderDetail) {
            Cart shoppingCart = detail.getCart();
            shoppingCart.setStatus(CartStatus.PAID);
            cartRepository.save(shoppingCart);
        }
    }

    private boolean validatePayment(Long orderId, PaymentRequestDTO paymentRequestDTO) {
        BigDecimal totalAmount = orderService.calculateTotalAmountOrderByOrderId(orderId);
        return (paymentRequestDTO.getAmount().compareTo(totalAmount) < 0);
    }
}