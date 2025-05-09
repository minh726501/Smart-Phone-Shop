package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.enity.Cart;
import com.bqminh.SmartPhoneShop.enity.Cart_Detail;
import com.bqminh.SmartPhoneShop.enity.Product;
import com.bqminh.SmartPhoneShop.enity.User;
import com.bqminh.SmartPhoneShop.repository.CartRepository;
import com.bqminh.SmartPhoneShop.repository.Cart_DetailRepository;
import com.bqminh.SmartPhoneShop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final Cart_DetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository, Cart_DetailRepository cartDetailRepository,UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService=userService;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product getProductById(long id){
        return productRepository.findById(id);
    }
    public void deleteProduct(long id){
         productRepository.deleteById(id);
    }
    public void addProductToCart(String email,long productid){
        //lay user
        User user=userService.getUserByEmail(email);
        if (user==null) {
            return;
        }
        //Tìm cart hiện tại của user
        Cart cart=cartRepository.findByUser(user);
        if (cart==null){
            Cart newCart=new Cart();
            newCart.setUser(user);
            newCart.setSum(1);
            cart=cartRepository.save(newCart);
        }
        //save Cart_detail
        Product product=productRepository.findById(productid);
        Cart_Detail cartDetail=new Cart_Detail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setPrice(product.getPrice());
        cartDetail.setQuantity(1);
        cartDetailRepository.save(cartDetail);
    }
}
