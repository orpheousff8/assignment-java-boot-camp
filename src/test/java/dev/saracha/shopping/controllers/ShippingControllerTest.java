package dev.saracha.shopping.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.*;
import dev.saracha.shopping.services.CustomerService;
import dev.saracha.shopping.services.OrderService;
import dev.saracha.shopping.services.ProductService;
import dev.saracha.shopping.services.ShippingService;
import dev.saracha.shopping.dtos.ShippingRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShippingControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    private Customer customer = TestHelper.getCustomer();
    private Shipping shipping = TestHelper.getShipping();

    @Test
    @DisplayName("Sent POST request to /shipping/shipment/?customerId=1&orderId=1 with shippingAddress(DTO) JSON then get HTTP.200 status")
    void makeShipment() throws JsonProcessingException {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        orderService.setOrderRepository(orderRepository);

        shippingRepository.save(shipping);
        shippingService.setCustomerService(customerService);
        shippingService.setOrderService(orderService);
        shippingService.setShippingRepository(shippingRepository);

        ShippingRequestDTO shippingRequestDTO = new ShippingRequestDTO(shipping);
        shippingRequestDTO.setIsCurrentAddress(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(shippingRequestDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        // Act
        ResponseEntity<Shipping> result = testRestTemplate.postForEntity("/shipping/shipment/?customerId=1&orderId=1", request, Shipping.class);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}