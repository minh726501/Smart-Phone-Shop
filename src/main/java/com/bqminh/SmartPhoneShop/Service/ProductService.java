package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.enity.Cart;
import com.bqminh.SmartPhoneShop.enity.Cart_Detail;
import com.bqminh.SmartPhoneShop.enity.Product;
import com.bqminh.SmartPhoneShop.enity.User;
import com.bqminh.SmartPhoneShop.repository.CartRepository;
import com.bqminh.SmartPhoneShop.repository.Cart_DetailRepository;
import com.bqminh.SmartPhoneShop.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void addProductToCart(String email, long productid, HttpSession session) {
        //lay user
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return;
        }
        //Tìm cart hiện tại của user
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setSum(0);
            cart = cartRepository.save(cart);

        }
        //save Cart_detail
        Product product = productRepository.findById(productid);
        //Kiểm tra sản phẩm đã có trong cart chưa
        Cart_Detail existingDetail = cartDetailRepository.findByCartAndProduct(cart, product);
        if (existingDetail == null) {
            Cart_Detail cartDetail = new Cart_Detail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setPrice(product.getPrice());
            cartDetail.setQuantity(1);
            cartDetailRepository.save(cartDetail);
            cart.setSum(cart.getSum() + 1);
            cartRepository.save(cart);
            session.setAttribute("sum", cart.getSum());
        } else {
            existingDetail.setQuantity(existingDetail.getQuantity() + 1);
            cartDetailRepository.save(existingDetail);
            session.setAttribute("sum", cart.getSum());
        }
    }
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }
    public void deleteCartDetail(long id,HttpSession session){
        Optional<Cart_Detail> cartDetailOptional=cartDetailRepository.findById(id);
        if (cartDetailOptional.isPresent()){
            Cart_Detail cartDetail=cartDetailOptional.get();
            Cart cart=cartDetail.getCart();
            cartDetailRepository.deleteById(id);
            if (cart.getSum()>1){
                int sum=cart.getSum()-1;
                cart.setSum(sum);
                session.setAttribute("sum",sum);
                cartRepository.save(cart);
            }else {
                cartDetailRepository.deleteById(id);
                session.setAttribute("sum",0);
            }
        }
        }
}
