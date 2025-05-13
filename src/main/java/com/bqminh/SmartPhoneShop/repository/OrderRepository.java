package com.bqminh.SmartPhoneShop.repository;

import com.bqminh.SmartPhoneShop.enity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
