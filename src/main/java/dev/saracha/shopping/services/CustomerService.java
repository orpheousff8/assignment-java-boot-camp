package dev.saracha.shopping.services;

import dev.saracha.shopping.domains.Customer;
import dev.saracha.shopping.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService() {
    }

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id).orElseThrow(IllegalArgumentException::new);
    }
}
