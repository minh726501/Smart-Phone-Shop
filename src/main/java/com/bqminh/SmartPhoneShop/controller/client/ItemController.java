package com.bqminh.SmartPhoneShop.controller.client;

import com.bqminh.SmartPhoneShop.Service.ProductService;
import com.bqminh.SmartPhoneShop.Service.UserService;
import com.bqminh.SmartPhoneShop.enity.Cart;
import com.bqminh.SmartPhoneShop.enity.Cart_Detail;
import com.bqminh.SmartPhoneShop.enity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {
    private final ProductService productService;
    private final UserService userService;

    public ItemController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long productId = id;
        String email = (String) session.getAttribute("email");
        productService.addProductToCart(email, productId, session);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //Long id=(Long) session.getAttribute("id");
        String email = (String) session.getAttribute("email");
        //User user=userService.getUserById(id);
        User user = userService.getUserByEmail(email);
        Cart cart = productService.getCartByUser(user);
        if (cart == null || cart.getCartDetails() == null) {
            model.addAttribute("cartDetails", new ArrayList<>());
            model.addAttribute("totalPrice", 0);
            return "client/cart/show";
        }
        List<Cart_Detail> cartDetails = cart.getCartDetails();
        double totalPrice = 0;
        for (Cart_Detail cartDetail : cartDetails) {
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        productService.deleteCartDetail(id, session);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkOutPage(@ModelAttribute("cart") Cart cart, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //Long id=(Long) session.getAttribute("id");
        String email = (String) session.getAttribute("email");
        //User user=userService.getUserById(id);
        User user = userService.getUserByEmail(email);
        Cart cartDB = productService.getCartByUser(user);
        if (cartDB == null || cart.getCartDetails() == null) {
            model.addAttribute("cartDetails", new ArrayList<>());
            model.addAttribute("totalPrice", 0);
            return "client/cart/show";
        }
        List<Cart_Detail> cartDetails = cartDB.getCartDetails();
        double totalPrice = 0;
        for (Cart_Detail cartDetail : cartDetails) {
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cartDB);
        productService.handleUpdateCartBeforeCheckout(cart.getCartDetails());

        return "client/cart/checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam("receiverName") String receiverName,
                             @RequestParam("receiverAddress") String receiverAddress,
                             @RequestParam("receiverPhone") String receiverPhone,
                             HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user = userService.getUserByEmail(email);
        productService.placeOrder(user,session,receiverName,receiverAddress,receiverPhone);
        return "redirect:/thanks";

    }
    @GetMapping("/thanks")
    public String thanksPage(){
        return "client/cart/thanks";
    }
}
