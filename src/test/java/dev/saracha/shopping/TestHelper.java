package dev.saracha.shopping;

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
        product.setPrice(BigDecimal.valueOf(9999.99d));
        product.setQuantity(99);

        return product;
    }

    public static Shipping getShipping() {
        Shipping shipping = new Shipping();

        shipping.setName("test name");
        shipping.setTelephoneNo("089-999-9999");
        shipping.setEmail("testEmail@test.com");
        shipping.setHouseNo("999/999");
        shipping.setSubDistrict("test-sub-district");
        shipping.setDistrict("test-district");
        shipping.setProvince("test-province");
        shipping.setZipCode("99999");

        return shipping;
    }
}
