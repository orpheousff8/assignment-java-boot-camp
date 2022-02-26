package dev.saracha.shopping.services;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.*;
import dtos.ShippingRequestDTO;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShippingServiceTest {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    private final Customer customer = TestHelper.getCustomer();
    private final Shipping shipping = TestHelper.getShipping();

    @Test
    @Description("Customer changed address")
    void makeShipment_new_address_is_success() {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        orderService.setOrderRepository(orderRepository);

        shippingRepository.save(shipping);

        Shipping newShipping = TestHelper.getShipping();
        newShipping.setStreet("new street");

        ShippingRequestDTO shippingRequestDTO = new ShippingRequestDTO(newShipping);
        shippingRequestDTO.setIsCurrentAddress(true);

        shippingService.setCustomerService(customerService);
        shippingService.setOrderService(orderService);
        shippingService.setShippingRepository(shippingRepository);
        // Act
        Shipping result = shippingService.makeShipment(shippingRequestDTO,2L,1L);
        // Assert
        assertEquals(customer.getName(),result.getCustomer().getName());
        assertEquals(newShipping.getStreet(),result.getStreet());
        assertEquals(OrderStatus.SHIPPING,result.getOrder().getStatus());
    }

    @Test
    @Description("Customer hasn't changed address")
    void makeShipment_same_address_is_success() {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        orderService.setOrderRepository(orderRepository);

        shippingRepository.save(shipping);

        ShippingRequestDTO shippingRequestDTO = new ShippingRequestDTO(shipping);
        shippingRequestDTO.setIsCurrentAddress(false);

        shippingService.setCustomerService(customerService);
        shippingService.setOrderService(orderService);
        shippingService.setShippingRepository(shippingRepository);
        // Act
        Shipping result = shippingService.makeShipment(shippingRequestDTO,2L,1L);
        // Assert
        assertEquals(customer.getName(),result.getCustomer().getName());
        assertEquals(shipping.getStreet(),result.getStreet());
        assertEquals(OrderStatus.SHIPPING,result.getOrder().getStatus());
    }
}