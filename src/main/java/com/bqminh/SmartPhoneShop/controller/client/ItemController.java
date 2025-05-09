package com.bqminh.SmartPhoneShop.controller.client;

import com.bqminh.SmartPhoneShop.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request){
        HttpSession session= request.getSession(false);
        long productId=id;
        String email= (String) session.getAttribute("email");
        productService.addProductToCart(email,productId);
        return "redirect:/";
    }
}
