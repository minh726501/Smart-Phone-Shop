package com.bqminh.Smart.Phone.Shop.Service;

import com.bqminh.Smart.Phone.Shop.enity.User;
import com.bqminh.Smart.Phone.Shop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User getUserById(long id){
        return userRepository.findById(id);
    }
}
