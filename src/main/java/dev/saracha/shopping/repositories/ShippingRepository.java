package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    Optional<Shipping> getShippingById(Long id);
}
