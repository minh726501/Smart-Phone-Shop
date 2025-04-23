package com.bqminh.Smart.Phone.Shop.repository;

import com.bqminh.Smart.Phone.Shop.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
}
