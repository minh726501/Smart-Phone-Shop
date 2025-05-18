package com.bqminh.SmartPhoneShop.controller.admin;

import com.bqminh.SmartPhoneShop.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {
    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model){
        model.addAttribute("countUsers",userService.countUser());
        model.addAttribute("countProducts",userService.countProduct());
        model.addAttribute("countOrders",userService.countOrder());
        return "admin/dashboard/show";
    }

}
