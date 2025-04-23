package com.bqminh.SmartPhoneShop.controller.admin;

import com.bqminh.SmartPhoneShop.Service.UserService;
import com.bqminh.SmartPhoneShop.enity.User;
import com.bqminh.SmartPhoneShop.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("admin/user/create")
    public String createUSerPage(Model model){
        model.addAttribute("newUser",new User());
        return "admin/user/create";
    }
    @PostMapping("admin/user/create")
    public String postUSerPage(Model model,@ModelAttribute ("newUser") User newUser ){
        userService.saveUser(newUser);
        return "redirect:/admin/user";
    }
    @GetMapping("/admin/user")
    public String getAllUser(Model model){
        List<User> users=userService.getAllUser();
        model.addAttribute("users",users);
        return "admin/user/show";
    }
    @GetMapping("/admin/user/view/{id}")
    public String detailUser(@PathVariable long id,Model model){
        User user=userService.getUserById(id);
        model.addAttribute("viewUser",user);
        return "admin/user/detail";
    }
    @GetMapping("admin/user/update/{id}")
    public String updateUser(@PathVariable long id,Model model){
        User getUserById=userService.getUserById(id);
        model.addAttribute("updateUser",getUserById);
        return "admin/user/update";
    }
    @PostMapping("admin/user/update")
    public String postUpdateUser(@ModelAttribute("updateUser") User updateUser){
        User getUser=userService.getUserById(updateUser.getId());
        getUser.setAddress(updateUser.getAddress());
        getUser.setEmail(updateUser.getEmail());
        getUser.setFullName(updateUser.getFullName());
        userService.saveUser(getUser);
        return "redirect:/admin/user";
    }
    @GetMapping("admin/user/delete/{id}")
    public String deleteUser(@PathVariable long id,Model model){
        User getUser=userService.getUserById(id);
        model.addAttribute("deleteUser",getUser);
        return "admin/user/delete";
    }
    @PostMapping("admin/user/delete/{id}")
    public String postDeleteUser(@PathVariable long id){
        User getUser=userService.getUserById(id);
        userService.deleteUser(getUser);
        return "redirect:/admin/user";
    }


}
