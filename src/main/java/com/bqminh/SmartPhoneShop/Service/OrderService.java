package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.enity.Order;
import com.bqminh.SmartPhoneShop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getListOrder(){
        return orderRepository.findAll();
    }
    public Order getOrderById(long id){
        return orderRepository.findById(id);
    }
    public void saveOrder(Order order){
        orderRepository.save(order);
    }
}
