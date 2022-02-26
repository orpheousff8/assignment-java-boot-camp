package dev.saracha.shopping.services;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.*;
import dtos.PaymentRequestDTO;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Description("This Class may need to run separately from other tests")
class PaymentServiceTest {
    @InjectMocks
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

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

    @Autowired
    private PaymentRepository paymentRepository;

    private final Customer customer = TestHelper.getCustomer();
    private final Product product = TestHelper.getProduct();

    @Test
    void makePayment_with_success() {
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

        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setMethod(PaymentMethod.CASH);
        paymentRequestDTO.setAmount(BigDecimal.valueOf(9999.99d));

        orderService.setOrderRepository(orderRepository);

        orderDetailService.setOrderDetailRepository(orderDetailRepository);

        paymentService.setOrderService(orderService);
        paymentService.setOrderDetailService(orderDetailService);
        paymentService.setPaymentRepository(paymentRepository);
        paymentService.setCartRepository(cartRepository);

        // Act
        Payment result = paymentService.makePayment(paymentRequestDTO, 1L);
        // Assert
        assertEquals(PaymentStatus.PAID, result.getStatus());
    }

    @Test
    void makePayment_with_failure() {
        assertThrows(IllegalArgumentException.class, () -> {
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

            PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
            paymentRequestDTO.setMethod(PaymentMethod.CASH);
            paymentRequestDTO.setAmount(BigDecimal.valueOf(9999.98d));

            orderService.setOrderRepository(orderRepository);

            orderDetailService.setOrderDetailRepository(orderDetailRepository);

            paymentService.setOrderService(orderService);
            paymentService.setOrderDetailService(orderDetailService);
            paymentService.setPaymentRepository(paymentRepository);
            paymentService.setCartRepository(cartRepository);

            // Act
            // Assert
            paymentService.makePayment(paymentRequestDTO, 1L);
        });
    }
}