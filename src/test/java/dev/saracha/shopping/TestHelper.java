package dev.saracha.shopping.repositories;

import dev.saracha.shopping.domains.*;

import java.math.BigDecimal;

public class TestHelper {
    public static Customer getCustomer() {
        Customer customer = new Customer();

        // id is automatically set
        customer.setName("test name");
        customer.setContact(getCustomerContact());

        return customer;
    }

    private static CustomerContact getCustomerContact() {
        CustomerContact customerContact = new CustomerContact();

        customerContact.setTelephoneNo("089-999-9999");
        customerContact.setEmail("testEmail@test.com");
        customerContact.setHouseNo("999/999");
        customerContact.setSubDistrict("test-sub-district");
        customerContact.setDistrict("test-district");
        customerContact.setProvince("test-province");
        customerContact.setZipCode("99999");

        return customerContact;
    }

    public static Product getProduct() {
        Product product = new Product();

        // id is automatically set
        product.setName("test-product-01");
        product.setPrice(new BigDecimal(9999.99));
        product.setQuantity(99);

        return product;
    }
}
