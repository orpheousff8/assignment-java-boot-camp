package dev.saracha.shopping.services;

import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.CartRepository;
import dev.saracha.shopping.repositories.OrderDetailRepository;
import dev.saracha.shopping.repositories.OrderRepository;
import dtos.ProductToCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public CartService() {
    }

    public CartService(ProductService productService, CustomerService customerService
            , CartRepository cartRepository
            , OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.productService = productService;
        this.customerService = customerService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional
    public List<Cart> addProductToCartList(List<ProductToCartDTO> products, Long customerId) {
        List<Cart> cartList = new ArrayList<>();
        for (ProductToCartDTO productDto : products) {
            Product product = productService.getProductById(productDto.getId());

            Customer customer = customerService.getCustomerById(customerId);

            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setCustomer(customer);
            cart.setOrderQuantity(productDto.getQuantity());
            cart.setTotalCost(calculateProductPrice(productDto.getQuantity(), product.getPrice()));
            cart.setStatus(CartStatus.EMPTY);
            cartList.add(cart);
        }
        return cartRepository.saveAll(cartList);
    }

    public List<Cart> getShoppingCardListByCustomerId(Long customerId) {
        return cartRepository.getShoppingCartListByCustomerId(customerId);
    }

    @Transactional
    public List<OrderDetail> checkoutProduct(List<Long> cartIdList, Long customerId) {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        Customer customer = customerService.getCustomerById(customerId);

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        Order newOrder = orderRepository.save(order);

        for (Long cartId : cartIdList) {
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(IllegalArgumentException::new);
            cart.setStatus(CartStatus.FILLED);
            cartRepository.save(cart);

            Product product = cart.getProduct();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCart(cart);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(cart.getOrderQuantity());
            orderDetail.setOrder(newOrder);

            orderDetailList.add(orderDetail);
        }
        return orderDetailRepository.saveAll(orderDetailList);
    }

    private static BigDecimal calculateProductPrice(Integer productQty, BigDecimal productPrice) {
        return productPrice.multiply(BigDecimal.valueOf(productQty));
    }
}
