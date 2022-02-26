package dev.saracha.shopping;

import dev.saracha.shopping.repositories.CustomerRepository;
import dev.saracha.shopping.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShoppingApplication {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ShoppingApplication.class, args);

        int count = context.getBeanDefinitionCount();
        System.out.println("Bean count:" + count);

    }
}
