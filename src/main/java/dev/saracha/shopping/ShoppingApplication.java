package dev.saracha.shopping;

import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.CustomerRepository;
import dev.saracha.shopping.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.saracha.shopping"})
@EntityScan("dev.saracha.shopping.domains")
@EnableJpaRepositories("dev.saracha.shopping.repositories")
public class ShoppingApplication {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void  initData(){
        //Customer contact
        CustomerContact customerContact = new CustomerContact();
        customerContact.setTelephoneNo("085-555-5555");
        customerContact.setEmail("customer@domain.com");
        customerContact.setHouseNo("55/555");
        customerContact.setSubDistrict("Silom");
        customerContact.setDistrict("Bang Rak");
        customerContact.setProvince("Bangkok");
        customerContact.setZipCode("10111");

        //Customer
        Customer customer = new Customer();
        // id is automatically set
        customer.setName("Chris Redfield");
        customer.setContact(customerContact);
        customerRepository.save(customer);

        //Products
        Product product1 = new Product();
        product1.setName("Resident Evil HD Edition");
        product1.setPrice(BigDecimal.valueOf(500.00d));
        product1.setQuantity(50);

        Product product2 = new Product();
        product2.setName("Resident Evil 2 Remake");
        product2.setPrice(BigDecimal.valueOf(900.00d));
        product2.setQuantity(100);

        Product product3 = new Product();
        product3.setName("Resident Evil 3 Remake");
        product3.setPrice(BigDecimal.valueOf(1200.00d));
        product3.setQuantity(150);

        Product product4 = new Product();
        product4.setName("Resident Evil 4");
        product4.setPrice(BigDecimal.valueOf(500.00d));
        product4.setQuantity(20);

        Product product5 = new Product();
        product5.setName("Resident Evil 5");
        product5.setPrice(BigDecimal.valueOf(550.00d));
        product5.setQuantity(25);

        List<Product> productList = Arrays.asList(product1,product2,product3,product4,product5);
        productRepository.saveAll(productList);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ShoppingApplication.class, args);

        int count = context.getBeanDefinitionCount();
        System.out.println("Bean count:" + count);

    }
}
