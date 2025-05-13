package com.bqminh.SmartPhoneShop.repository;

import com.bqminh.SmartPhoneShop.enity.Order_Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<Order_Product ,Long> {
}
