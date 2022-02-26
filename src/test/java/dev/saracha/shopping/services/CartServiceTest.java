package dev.saracha.shopping.services;

import dev.saracha.shopping.TestHelper;
import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.*;
import dtos.ProductToCartDTO;
import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private final Customer customer = TestHelper.getCustomer();
    private final Product product = TestHelper.getProduct();

    @Test
    void addProductToCart() {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        productRepository.save(product);
        productService.setProductRepository(productRepository);

        Cart cart = new Cart();
        // id is automatically set
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setOrderQuantity(0);
        cart.setTotalCost(BigDecimal.valueOf(0d));
        cart.setStatus(CartStatus.FILLED);
        cartRepository.save(cart);

        Order order = new Order();
        order.setCustomer(customer);
        orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCart(cart);
        orderDetail.setOrder(order);
        orderDetailRepository.save(orderDetail);

        cartService.setProductService(productService);
        cartService.setCustomerService(customerService);
        cartService.setCartRepository(cartRepository);
        cartService.setOrderRepository(orderRepository);
        cartService.setOrderDetailRepository(orderDetailRepository);

        ProductToCartDTO productToCartDTO = new ProductToCartDTO();
        productToCartDTO.setId(6L);
        productToCartDTO.setQuantity(2);

        List<ProductToCartDTO> productToCartDTOList = Collections.singletonList(productToCartDTO);

        // Act
        List<Cart> result = cartService.addProductToCartList(productToCartDTOList, 1L);

        // Assert
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getOrderQuantity());
        assertEquals(BigDecimal.valueOf(19999.98), result.get(0).getTotalCost());
        assertEquals(CartStatus.EMPTY, result.get(0).getStatus());
    }

    @Disabled
    @Description("Same test as CartRepository")
    @Test
    void getShoppingCardDataByCustomerId() {

    }

    @Test
    void checkoutProduct() {
        // Arrange
        customerRepository.save(customer);
        customerService.setCustomerRepository(customerRepository);

        productRepository.save(product);
        productService.setProductRepository(productRepository);

        Cart cart1 = new Cart();
        // id is automatically set
        cart1.setCustomer(customer);
        cart1.setProduct(product);
        cart1.setOrderQuantity(1);
        cart1.setTotalCost(BigDecimal.valueOf(9999.99d));
        cart1.setStatus(CartStatus.FILLED);
        cartRepository.save(cart1);

        Cart cart2 = new Cart();
        // id is automatically set
        cart2.setCustomer(customer);
        cart2.setProduct(product);
        cart2.setOrderQuantity(10);
        cart2.setTotalCost(BigDecimal.valueOf(99999.90d));
        cart2.setStatus(CartStatus.FILLED);
        cartRepository.save(cart2);

        Order order = new Order();
        order.setCustomer(customer);
        orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCart(cart2);
        orderDetail.setOrder(order);
        orderDetailRepository.save(orderDetail);

        cartService.setProductService(productService);
        cartService.setCustomerService(customerService);
        cartService.setCartRepository(cartRepository);
        cartService.setOrderRepository(orderRepository);
        cartService.setOrderDetailRepository(orderDetailRepository);

        List<Long> cartIdList = Arrays.asList(1L, 2L);

        // Act
        List<OrderDetail> result = cartService.checkoutProduct(cartIdList, 2L);

        // Assert
        assertEquals(2, result.size());

        assertEquals(customer.getName(), result.get(0).getOrder().getCustomer().getName());
        assertEquals(OrderStatus.PENDING, result.get(0).getOrder().getStatus());
        assertEquals(product.getName(), result.get(0).getProduct().getName());
        assertEquals(1L, result.get(0).getCart().getId());
        assertEquals(1, result.get(0).getCart().getOrderQuantity());
        assertEquals(0, BigDecimal.valueOf(9999.99d).compareTo(result.get(0).getCart().getTotalCost()));
        assertEquals(CartStatus.FILLED, result.get(0).getCart().getStatus());


        assertEquals(customer.getName(), result.get(1).getOrder().getCustomer().getName());
        assertEquals(OrderStatus.PENDING, result.get(1).getOrder().getStatus());
        assertEquals(product.getName(), result.get(1).getProduct().getName());
        assertEquals(2L, result.get(1).getCart().getId());
        assertEquals(1, result.get(1).getCart().getOrderQuantity());
        assertEquals(0, BigDecimal.valueOf(9999.99d).compareTo(result.get(1).getCart().getTotalCost()));
        assertEquals(CartStatus.FILLED, result.get(1).getCart().getStatus());
    }
}