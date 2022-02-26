package dev.saracha.shopping.services;

import dev.saracha.shopping.domains.Product;
import dev.saracha.shopping.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId) {
        return productRepository.getProductById(productId).orElseThrow(IllegalArgumentException::new);
    }

    public List<Product> getProductListByName(String name) {
        return productRepository.getProductListByNameContainingIgnoreCase(name);
    }
}
