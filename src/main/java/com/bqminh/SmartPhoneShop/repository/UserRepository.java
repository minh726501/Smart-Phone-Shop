package com.bqminh.SmartPhoneShop.repository;

import com.bqminh.SmartPhoneShop.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getUserById(Long id);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
