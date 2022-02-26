package dev.saracha.shopping.services;

import dev.saracha.shopping.domains.*;
import dev.saracha.shopping.repositories.ShippingRepository;
import dtos.ShippingRequestDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShippingService {
    private CustomerService customerService;
    private OrderService orderService;
    private ShippingRepository shippingRepository;

    public ShippingService() {
    }

    public ShippingService(CustomerService customerService, OrderService orderService, ShippingRepository shippingRepository) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.shippingRepository = shippingRepository;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setShippingRepository(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Transactional
    public Shipping makeShipment(ShippingRequestDTO shippingRequestDTO, Long customerId, Long orderId) {
        Shipping shipping = new Shipping();

        Customer customer = customerService.getCustomerById(customerId);

        Order order = orderService.getOrderById(orderId);
        order.setStatus(OrderStatus.SHIPPING);
        order = orderService.updateOrder(order);

        shipping.setCustomer(customer);
        shipping.setOrder(order);

        if (shippingRequestDTO.isCurrentAddress()) {
            shipping.setName(shippingRequestDTO.getName());
            shipping.setHouseNo(shippingRequestDTO.getHouseNo());
            shipping.setMoo(shippingRequestDTO.getMoo());
            shipping.setStreet(shippingRequestDTO.getStreet());
            shipping.setSubDistrict(shippingRequestDTO.getSubDistrict());
            shipping.setDistrict(shippingRequestDTO.getDistrict());
            shipping.setProvince(shippingRequestDTO.getProvince());
            shipping.setZipCode(shippingRequestDTO.getZipCode());
            shipping.setEmail(shippingRequestDTO.getEmail());
            shipping.setTelephoneNo(shippingRequestDTO.getTelephoneNo());
        } else {
            CustomerContact customerContact = customer.getContact();
            shipping.setName(customer.getName());
            shipping.setHouseNo(customerContact.getHouseNo());
            shipping.setMoo(customerContact.getMoo());
            shipping.setStreet(customerContact.getStreet());
            shipping.setSubDistrict(customerContact.getSubDistrict());
            shipping.setDistrict(customerContact.getDistrict());
            shipping.setProvince(customerContact.getProvince());
            shipping.setZipCode(customerContact.getZipCode());
            shipping.setEmail(customerContact.getEmail());
            shipping.setTelephoneNo(customerContact.getTelephoneNo());
        }
        return shippingRepository.save(shipping);
    }
}
