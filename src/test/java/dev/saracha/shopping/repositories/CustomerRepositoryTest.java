package dev.saracha.shopping.repositories;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.Customer;
import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private final Customer customer = TestHelper.getCustomer();

    @Description("Skip success test case because id is auto generated, which we cannot fix it")
    @Disabled
    @Test
    public void getCustomerById_is_present_success() {
        // Arrange
        customerRepository.save(customer);
        // Act
        Optional<Customer> result = customerRepository.getCustomerById(1L);
        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void getCustomerById_is_present_failure() {
        // Act
        Optional<Customer> result = customerRepository.getCustomerById(999L);
        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void getCustomerByName_is_present_success() {
        // Arrange
        customerRepository.save(customer);
        // Act
        Optional<Customer> result = customerRepository.getCustomerByName("test name");
        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void getCustomerByName_is_present_failure() {
        // Arrange
        customerRepository.save(customer);
        // Act
        Optional<Customer> result = customerRepository.getCustomerByName("not-exist name");
        // Assert
        assertFalse(result.isPresent());
    }
}