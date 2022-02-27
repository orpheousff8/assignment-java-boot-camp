package dev.saracha.shopping.controllerAdvices;

import dev.saracha.shopping.ShoppingApplicationResponse;
import dev.saracha.shopping.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ShoppingApplicationResponse productNotFound(Exception e) {
        return new ShoppingApplicationResponse("Product " + e.getMessage());
    }
}
