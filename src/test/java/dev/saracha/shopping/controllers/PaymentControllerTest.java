package dev.saracha.shopping.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.*;
import dev.saracha.shopping.services.OrderDetailService;
import dev.saracha.shopping.services.OrderService;
import dev.saracha.shopping.services.PaymentService;
import dev.saracha.shopping.dtos.PaymentRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private final Customer customer = TestHelper.getCustomer();
    private final Product product = TestHelper.getProduct();

    @Test
    @DisplayName("send POST request to /payment/?orderId=1 and get 'PAID' in body message")
    void makePayment_is_success() throws JsonProcessingException {
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

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(paymentRequestDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        // Act
        ResponseEntity<Payment> result = testRestTemplate.postForEntity("/payment/?orderId=1", request, Payment.class);
        // Assert
        assertEquals(PaymentStatus.PAID, result.getBody().getStatus());
    }
}