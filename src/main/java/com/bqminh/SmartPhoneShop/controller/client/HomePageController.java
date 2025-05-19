package com.bqminh.SmartPhoneShop.controller.client;

import com.bqminh.SmartPhoneShop.Service.OrderService;
import com.bqminh.SmartPhoneShop.Service.ProductService;
import com.bqminh.SmartPhoneShop.Service.UserService;
import com.bqminh.SmartPhoneShop.dto.RegisterDTO;
import com.bqminh.SmartPhoneShop.enity.Order;
import com.bqminh.SmartPhoneShop.enity.Order_Product;
import com.bqminh.SmartPhoneShop.enity.Product;
import com.bqminh.SmartPhoneShop.enity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;


    public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;

    }

    @GetMapping("/")
    public String getHomePage(Model model){
        List<Product> getAllProduct=productService.getAllProduct();
        model.addAttribute("products",getAllProduct);
        return "client/homepage/hello";
    }
    @GetMapping("product/{id}")
    public String getProduct(Model model, @PathVariable long id){
        Product getProduct=productService.getProductById(id);
        model.addAttribute("detailProduct",getProduct);
        return "client/product/detail";
    }
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userRegister",new RegisterDTO());
        return "client/auth/register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("userRegister") @Valid RegisterDTO registerDTO, BindingResult bindingResult){
        List<FieldError>errors=bindingResult.getFieldErrors();
        for (FieldError error:errors){
            System.out.println(">>>>"+error.getField()+"-"+error.getDefaultMessage()+"<<<<");
        }
        if (bindingResult.hasErrors()){
            return "client/auth/register";
        }
        User user=userService.registerDTOtoUser(registerDTO);
         String hashPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(userService.getRoleByName("USER"));
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "client/auth/login";
    }
    @GetMapping("/access-deny")
    public String getDenyPage(){
        return "client/auth/deny";
    }
    @GetMapping("order-history")
    public String historyPage(Model model,HttpServletRequest request){
        HttpSession session=request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user=userService.getUserByEmail(email);
        List<Order> order=orderService.getOrderByUser(user);
        model.addAttribute("orders",order);
        return "client/cart/order-history";
    }


}
