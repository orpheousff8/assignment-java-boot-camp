package dev.saracha.shopping.repositories;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.Product;
import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private final Product product = TestHelper.getProduct();

    @Description("Skip some tests related to findById because IDs are auto generated ordered by test running sequence, which we don't know.")
    @Disabled
    @Test
    public void getProductById_is_present_success() {
        // Arrange
        productRepository.save(product);
        // Act
        Optional<Product> result = productRepository.getProductById(1L);
        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void getProductById_is_present_failure() {
        // Act
        Optional<Product> result = productRepository.getProductById(999L);
        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void getProductListByNameContainingIgnoreCase_is_not_empty_with_success() {
        // Arrange
        productRepository.save(product);
        // Act
        List<Product> result = productRepository.getProductListByNameContainingIgnoreCase("test-product");
        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    void getProductListByNameContainingIgnoreCase_is_not_empty_with_failure() {
        // Arrange
        productRepository.save(product);
        // Act
        List<Product> result = productRepository.getProductListByNameContainingIgnoreCase("not-exists-product");
        // Assert
        assertTrue(result.isEmpty());
    }
}