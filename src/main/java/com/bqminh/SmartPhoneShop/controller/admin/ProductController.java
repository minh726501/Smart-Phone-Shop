package com.bqminh.SmartPhoneShop.controller.admin;

import com.bqminh.SmartPhoneShop.Service.ProductService;
import com.bqminh.SmartPhoneShop.enity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model){
        List<Product> getAllProduct=productService.getAllProduct();
        model.addAttribute("product",getAllProduct);
        return "admin/product/show";
    }
    @GetMapping("/admin/product/create")
    public String createProduct(Model model){
        model.addAttribute("newProduct",new Product());
        return "admin/product/create";
    }
    @PostMapping("/admin/product/create")
    public String postProduct(@ModelAttribute("newProduct") Product product){
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }
}
