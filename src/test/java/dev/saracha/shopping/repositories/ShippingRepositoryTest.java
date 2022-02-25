package dev.saracha.shopping.repositories;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.Shipping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShippingRepositoryTest {

    @Autowired
    private ShippingRepository shippingRepository;

    @Test
    void getShippingById_is_present_with_success() {
        // Arrange
        Shipping shipping = TestHelper.getShipping();
        shippingRepository.save(shipping);
        // Act
        Optional<Shipping> result = shippingRepository.getShippingById(1L);
        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void getShippingById_is_present_with_failure() {
        // Act
        Optional<Shipping> result = shippingRepository.getShippingById(999L);
        // Assert
        assertFalse(result.isPresent());
    }
}