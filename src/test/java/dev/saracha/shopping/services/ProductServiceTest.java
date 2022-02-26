package dev.saracha.shopping.services;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.Product;
import dev.saracha.shopping.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void getProductById_is_not_null() {
        // Arrange
        Product product = TestHelper.getProduct();
        when(productRepository.getProductById(1L)).thenReturn(Optional.ofNullable(product));
        // Act
        Product result = productService.getProductById(1L);
        // Assert
        assertNotNull(result);
    }

    @Test
    void getProductListByName_is_not_empty() {
        // Arrange
        Product product = TestHelper.getProduct();
        List<Product> productList = Collections.singletonList(product);
        when(productRepository.getProductListByNameContainingIgnoreCase("test-product-01")).thenReturn(productList);
        // Act
        List<Product> result = productService.getProductListByName("test-product-01");
        // Assert
        assertFalse(result.isEmpty());
    }
}