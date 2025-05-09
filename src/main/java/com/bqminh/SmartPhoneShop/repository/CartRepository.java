package com.bqminh.SmartPhoneShop.repository;

import com.bqminh.SmartPhoneShop.enity.Cart;
import com.bqminh.SmartPhoneShop.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUser(User user);

}
