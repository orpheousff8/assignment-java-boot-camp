package dev.saracha.shopping.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name){
        super(name);
    }
}
