package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.Cart;
import dev.saracha.shopping.domains.CartStatus;
import dev.saracha.shopping.domains.Customer;
import dev.saracha.shopping.domains.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    private final Customer customer = TestHelper.getCustomer();
    private final Product product = TestHelper.getProduct();

    @Test
    void findShoppingCartDataByCustomerId_is_not_empty_success() {
        // Arrange
        customerRepository.save(customer);
        productRepository.save(product);

        Cart cart = new Cart();
        // id is automatically set
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setOrderQuantity(1);
        cart.setStatus(CartStatus.FILLED);
        cartRepository.save(cart);

        // Act
        List<Cart> result = cartRepository.getShoppingCartsByCustomerId(1L);

        //Arrange
        assertFalse(result.isEmpty());
    }

    @Test
    void findShoppingCartDataByCustomerId_is_not_empty_failure() {
        // Act
        List<Cart> result = cartRepository.getShoppingCartsByCustomerId(1L);

        //Arrange
        assertTrue(result.isEmpty());
    }
}