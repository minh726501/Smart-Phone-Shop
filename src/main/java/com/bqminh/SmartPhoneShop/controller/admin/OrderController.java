package com.bqminh.SmartPhoneShop.controller.admin;

import com.bqminh.SmartPhoneShop.Service.OrderService;
import com.bqminh.SmartPhoneShop.enity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String orderPage(Model model){
        List<Order>getAllOrder=orderService.getListOrder();
        model.addAttribute("order",getAllOrder);
        return "admin/order/show";
    }
}
