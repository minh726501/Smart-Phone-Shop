package com.bqminh.SmartPhoneShop.repository;

import com.bqminh.SmartPhoneShop.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
}
