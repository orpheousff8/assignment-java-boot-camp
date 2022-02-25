package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> getPaymentById(Long id);
}
