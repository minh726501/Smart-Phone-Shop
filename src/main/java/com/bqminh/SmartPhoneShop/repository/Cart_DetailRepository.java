package com.bqminh.SmartPhoneShop.repository;

import com.bqminh.SmartPhoneShop.enity.Cart;
import com.bqminh.SmartPhoneShop.enity.Cart_Detail;
import com.bqminh.SmartPhoneShop.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cart_DetailRepository extends JpaRepository<Cart_Detail,Long> {
    Cart_Detail findByCartAndProduct(Cart cart, Product product);
}
