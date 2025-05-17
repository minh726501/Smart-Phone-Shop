package com.bqminh.SmartPhoneShop.controller.admin;

import com.bqminh.SmartPhoneShop.Service.OrderService;
import com.bqminh.SmartPhoneShop.enity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/admin/order/view/{id}")
    public String detailOrder(@PathVariable long id, Model model){
        Order order=orderService.getOrderById(id);
        model.addAttribute("viewOrder",order);
        return "admin/order/detail";
    }
    @GetMapping("/admin/order/update/{id}")
    public String updateOrder(@PathVariable long id,Model model){
        Order updateOrder=orderService.getOrderById(id);
        model.addAttribute("updateOrder",updateOrder);
        return "admin/order/update";
    }
    @PostMapping("/admin/order/update")
    public String postOrder(@ModelAttribute("updateOrder")Order updateOrder){
        Order getOrder=orderService.getOrderById(updateOrder.getId());
        getOrder.setReceiverName(updateOrder.getReceiverName());
        getOrder.setReceiverAddress(updateOrder.getReceiverAddress());
        getOrder.setReceiverPhone(updateOrder.getReceiverPhone());
        getOrder.setStatus(updateOrder.getStatus());
        orderService.saveOrder(getOrder);
        return "redirect:/admin/order";
    }
    @GetMapping("/admin/order/delete/{id}")
    public String deleteOrder(@PathVariable long id,Model model){
        Order order=orderService.getOrderById(id);
        model.addAttribute("deleteOrder",order);
        return "admin/order/delete";
    }
    @PostMapping("/admin/order/delete/{id}")
    public String postDeleteOrder(@PathVariable long id){
        Order getOrder=orderService.getOrderById(id);
        orderService.deleteOrderById(getOrder.getId());
        return "redirect:/admin/order";
    }

}
