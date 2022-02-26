package dev.saracha.shopping.services;

import dev.saracha.shopping.domains.OrderDetail;
import dev.saracha.shopping.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailService() {
    }

    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getOrderDetailListByOrderId(Long orderId){
        return orderDetailRepository.getOrderDetailListByOrderId(orderId);
    }
}
