package com.bqminh.SmartPhoneShop.controller.admin;

import com.bqminh.SmartPhoneShop.Service.UploadService;
import com.bqminh.SmartPhoneShop.Service.UserService;
import com.bqminh.SmartPhoneShop.enity.Role;
import com.bqminh.SmartPhoneShop.enity.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    //private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService/*PasswordEncoder passwordEncoder*/) {
        this.userService = userService;
        this.uploadService = uploadService;
        //this.passwordEncoder=passwordEncoder;
    }


    @GetMapping("admin/user/create")
    public String createUSerPage(Model model){
        model.addAttribute("newUser",new User());
        return "admin/user/create";
    }
    @PostMapping("admin/user/create")
    public String postUserPage(@ModelAttribute ("newUser") User newUser, @RequestParam("bqmFile")MultipartFile file){
        String avatar=uploadService.uploadFile(file,"avatar");
       // String hashPassword=passwordEncoder.encode(newUser.getPassword());
        newUser.setAvatar(avatar);
       // newUser.setPassword(hashPassword);
        newUser.setRole(userService.getRoleByName(newUser.getRole().getName()));
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
        Role role=userService.getRoleByName(updateUser.getRole().getName());
        getUser.setRole(role);
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
