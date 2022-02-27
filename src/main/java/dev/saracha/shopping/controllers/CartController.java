package dev.saracha.shopping.controllers;

import dev.saracha.shopping.domains.Cart;
import dev.saracha.shopping.domains.OrderDetail;
import dev.saracha.shopping.services.CartService;
import dev.saracha.shopping.dtos.ProductToCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<List<Cart>> shoppingCard(@RequestParam("customerId") Long customerId) {
        List<Cart> shoppingCarts = cartService.getShoppingCardListByCustomerId(customerId);
        return ResponseEntity.ok().body(shoppingCarts);
    }

    @PostMapping(value = "/",  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Cart>> addProductToCart(@RequestParam("customerId") Long customerId, @RequestBody List<ProductToCartDTO> productToCartDTOS) {
        List<Cart> cartList = cartService.addProductToCartList(productToCartDTOS, customerId);
        return ResponseEntity.ok().body(cartList);
    }

    @PostMapping("/cart-checkout")
    public ResponseEntity<List<OrderDetail>> addProductToCart(@RequestBody List<Long> cartList, @RequestParam("customerId") Long customerId) {
        List<OrderDetail> orderDetailList = cartService.checkoutProduct(cartList, customerId);
        return ResponseEntity.ok().body(orderDetailList);
    }
}