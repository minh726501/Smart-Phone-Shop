package com.bqminh.Smart.Phone.Shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/admin/user")
    public String getUserPage(){
        return "admin/user/show";
    }
    @GetMapping("admin/user/create")
    public String createUSerPage(Model model){
        model.addAttribute("newUser",new )
        return "admin/user/create";
    }
    @PostMapping("admin/user/create")
    public String postUSerPage(ModelAttribute ("newUser") ){
        return "redirect:admin/user";
    }
}
