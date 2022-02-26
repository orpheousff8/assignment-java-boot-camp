package dev.saracha.shopping.services;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.Customer;
import dev.saracha.shopping.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    Customer customer = TestHelper.getCustomer();

    @Test
    void getCustomerById() {
        // Arrange
        when(customerRepository.getCustomerById(1L)).thenReturn(Optional.of(customer));
        // Act
        Customer result = customerService.getCustomerById(1L);
        // Assert
        assertEquals(customer.getName(), result.getName());
    }
}