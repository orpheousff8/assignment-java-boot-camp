package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> getCustomerById(Long customerId);

    Optional<Customer> getCustomerByName(String customerName);
}
