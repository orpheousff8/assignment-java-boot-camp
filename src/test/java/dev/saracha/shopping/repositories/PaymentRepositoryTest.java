package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void getPaymentById_is_present_success() {
        // Arrange
        Payment payment = new Payment();
        paymentRepository.save(payment);
        // Act
        Optional<Payment> result = paymentRepository.getPaymentById(1L);
        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    public void getPaymentById_is_present_failure() {
        // Act
        Optional<Payment> result = paymentRepository.getPaymentById(999L);
        // Assert
        assertFalse(result.isPresent());
    }
}