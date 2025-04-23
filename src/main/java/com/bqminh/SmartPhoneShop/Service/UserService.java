package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.enity.User;
import com.bqminh.SmartPhoneShop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteUser(User user){
        userRepository.delete(user);
    }

}
