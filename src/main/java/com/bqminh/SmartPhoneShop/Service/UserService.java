package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.dto.RegisterDTO;
import com.bqminh.SmartPhoneShop.enity.Role;
import com.bqminh.SmartPhoneShop.enity.User;
import com.bqminh.SmartPhoneShop.repository.RoleRepository;
import com.bqminh.SmartPhoneShop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User getUserById(long id){
        return userRepository.getUserById(id);
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    public Role getRoleByName(String role){
        return roleRepository.getRoleByName(role);
    }
    public User registerDTOtoUser(RegisterDTO registerDTO){
        User user=new User();
        user.setFullName(registerDTO.getFirstName()+" "+registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

}
