package dev.saracha.shopping.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.CartRepository;
import dev.saracha.shopping.repositories.CustomerRepository;
import dev.saracha.shopping.repositories.ProductRepository;
import dev.saracha.shopping.services.CustomerService;
import dev.saracha.shopping.services.ProductService;
import dtos.ProductToCartDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    private final Customer customer = TestHelper.getCustomer();
    private final Product product = TestHelper.getProduct();

    @Test
    @DisplayName("Sent GET request to /cart/?customerId=1 then get HTTP.200 status")
    void shoppingCard() {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        productRepository.save(product);
        productService.setProductRepository(productRepository);

        Cart cart = new Cart();
        // id is automatically set
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setOrderQuantity(1);
        cart.setTotalCost(product.getPrice());
        cart.setStatus(CartStatus.FILLED);
        cartRepository.save(cart);

        // Act
        ResponseEntity<List<Cart>> result = testRestTemplate.getForEntity("/cart/?customerId=1", TestHelper.getObjectTypeOfCartList());

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Sent POST request to /cart/?customerId=1 with product+quantity in JSON then get HTTP.200 status")
    void addProductToCart_requestBody_productToCartDTO() throws JsonProcessingException {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        productRepository.save(product);
        productService.setProductRepository(productRepository);

        Cart cart = new Cart();
        // id is automatically set
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setOrderQuantity(1);
        cart.setTotalCost(product.getPrice());
        cart.setStatus(CartStatus.FILLED);
        cartRepository.save(cart);

        ProductToCartDTO productToCartDTO1 = new ProductToCartDTO();
        productToCartDTO1.setId(1L);
        productToCartDTO1.setQuantity(5);

        ProductToCartDTO productToCartDTO2 = new ProductToCartDTO();
        productToCartDTO2.setId(2L);
        productToCartDTO2.setQuantity(10);

        List<ProductToCartDTO> products = Arrays.asList(productToCartDTO1, productToCartDTO2);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(products);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        // Act
        ResponseEntity<List<Cart>> result = testRestTemplate.postForEntity("/cart/?customerId=1", request, TestHelper.getObjectTypeOfCartList());

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Sent POST request to /cart/cart-checkout/?customerId=1 with a list of cartId in JSON then get HTTP.200 status")
    void testAddProductToCart_requestBody_requestBody_cartList() throws JsonProcessingException {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        productRepository.save(product);
        productService.setProductRepository(productRepository);

        Cart cart = new Cart();
        // id is automatically set
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setOrderQuantity(1);
        cart.setTotalCost(product.getPrice());
        cart.setStatus(CartStatus.FILLED);
        cartRepository.save(cart);

        List<Long> cartIdList = Arrays.asList(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cartIdList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        // Act
        ResponseEntity<List<OrderDetail>> result = testRestTemplate.postForEntity("/cart/cart-checkout/?customerId=1", request, TestHelper.getObjectTypeOfOrderDetailList());

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}