package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.enity.Order;
import com.bqminh.SmartPhoneShop.enity.Order_Product;
import com.bqminh.SmartPhoneShop.repository.OrderProductRepository;
import com.bqminh.SmartPhoneShop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
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
    public void deleteOrderById(long id){
        Order order=orderRepository.findById(id);
        List<Order_Product> orderProducts=order.getOrderProducts();
        for (Order_Product oP:orderProducts){
            orderProductRepository.delete(oP);
        }
        orderRepository.deleteById(id);

    }
}
