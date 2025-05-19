package com.bqminh.SmartPhoneShop.repository;

import com.bqminh.SmartPhoneShop.enity.Order;
import com.bqminh.SmartPhoneShop.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findById(long id);
    List<Order> findByUser(User user);

}
