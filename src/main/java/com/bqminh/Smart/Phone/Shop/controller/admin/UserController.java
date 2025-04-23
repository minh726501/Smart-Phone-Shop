package com.bqminh.Smart.Phone.Shop.controller.admin;

import com.bqminh.Smart.Phone.Shop.Service.UserService;
import com.bqminh.Smart.Phone.Shop.enity.User;
import com.bqminh.Smart.Phone.Shop.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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


}
