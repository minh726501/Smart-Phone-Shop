package com.bqminh.SmartPhoneShop.controller.admin;

import com.bqminh.SmartPhoneShop.Service.ProductService;
import com.bqminh.SmartPhoneShop.Service.UploadService;
import com.bqminh.SmartPhoneShop.enity.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService,UploadService uploadService) {
        this.productService = productService;
        this.uploadService=uploadService;
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
    public String postProduct(@ModelAttribute("newProduct")@Valid Product product, BindingResult bindingResult, @RequestParam("bqmFile")MultipartFile file){
        List<FieldError>errors=bindingResult.getFieldErrors();
        for (FieldError error:errors){
            System.out.println(">>>>"+error.getField()+"-"+error.getDefaultMessage()+"<<<<");
        }
        if (bindingResult.hasErrors()){
            return "admin/product/create";
        }
        String imageProduct=uploadService.upLoadFile(file,"product");
        product.setImage(imageProduct);
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id,Model model){
        Product updateProduct=productService.getProductById(id);
        model.addAttribute("updateProduct",updateProduct);
        return"admin/product/update";
    }
    @PostMapping("/admin/product/update")
    public String postUpdateProduct(@ModelAttribute("updateProduct")Product updateProduct){
        Product getProduct=productService.getProductById(updateProduct.getId());
        getProduct.setName(updateProduct.getName());
        getProduct.setPrice(updateProduct.getPrice());
        getProduct.setShortDesc(updateProduct.getShortDesc());
        getProduct.setDetailDesc(updateProduct.getDetailDesc());
        getProduct.setQuantity(updateProduct.getQuantity());
        getProduct.setFactory(updateProduct.getFactory());
        productService.saveProduct(getProduct);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/view/{id}")
    public String viewProduct(@PathVariable long id,Model model){
        Product getProduct=productService.getProductById(id);
        model.addAttribute("viewProduct",getProduct);
        return"admin/product/detail";
    }
}
