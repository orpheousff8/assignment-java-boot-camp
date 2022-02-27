package dev.saracha.shopping.controllers;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.Product;
import dev.saracha.shopping.repositories.ProductRepository;
import dev.saracha.shopping.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private final Product product = TestHelper.getProduct();

    @Test
    @DisplayName("Send GET request to /product/test and get something")
    void getProductByName_is_not_empty() {
        // Arrange
        productRepository.save(product);
        productService.setProductRepository(productRepository);
        // Act
        List<Product> result = testRestTemplate.getForObject("/product/test", TestHelper.getObjectTypeOfProduct());
        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Send GET request to /product/test and get nothing")
    void getProductByName_is_empty() {
        // Act
        List<Product> result = testRestTemplate.getForObject("/product/not-exist", TestHelper.getObjectTypeOfProduct());
        // Assert
        assertTrue(result.isEmpty());
    }
}