package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.enity.*;
import com.bqminh.SmartPhoneShop.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final Cart_DetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository, Cart_DetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
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
    public void handleUpdateCartBeforeCheckout(List<Cart_Detail> cartDetails) {
        // Duyệt qua tất cả các chi tiết giỏ hàng
        for (Cart_Detail cartDetail : cartDetails) {
            // Tìm CartDetail bằng ID
            Optional<Cart_Detail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());

            // Kiểm tra nếu CartDetail tồn tại
            if (cdOptional.isPresent()) {
                Cart_Detail currentCartDetail = cdOptional.get();  // Lấy CartDetail hiện tại

                // Cập nhật số lượng sản phẩm trong giỏ hàng
                currentCartDetail.setQuantity(cartDetail.getQuantity());

                // Lưu lại CartDetail đã cập nhật
                this.cartDetailRepository.save(currentCartDetail);
            }

        }

    }
    public void placeOrder(User user,HttpSession session,String receiverName,String receiverAddress,String receiverPhone){
        Cart cart=cartRepository.findByUser(user);
        if (cart != null) {
            // Lấy danh sách chi tiết giỏ hàng
            List<Cart_Detail> cartDetails = cart.getCartDetails();
            // Tạo đối tượng đơn hàng
            Order order = new Order();
            order.setUser(user);
            order.setReceiverName(receiverName);
            order.setReceiverAddress(receiverAddress);
            order.setReceiverPhone(receiverPhone);
            order.setStatus("PENDING");
            double totalPrice = 0;
            for (Cart_Detail cd : cartDetails) {
                totalPrice += cd.getPrice() * cd.getQuantity();  // Cập nhật công thức tính tiền
            }
            order.setTotalPrice(totalPrice);  // Gán tổng tiền cho đơn hàng
            // Lưu đơn hàng
            orderRepository.save(order);
            // Lưu chi tiết đơn hàng từ giỏ hàng
            for (Cart_Detail cd : cartDetails) {
                Order_Product orderDetail = new Order_Product();
                orderDetail.setOrder(order);
                orderDetail.setProduct(cd.getProduct());
                orderDetail.setPrice(cd.getPrice());
                orderDetail.setQuantity(cd.getQuantity());

                // Lưu chi tiết đơn hàng
                orderProductRepository.save(orderDetail);
            }

            // Xóa chi tiết giỏ hàng
            cartDetailRepository.deleteAll(cartDetails);

            // Xóa giỏ hàng
            cart.setCartDetails(new ArrayList<>());
            cart.setSum(0);
            cartRepository.save(cart);


            // Cập nhật lại session
            session.setAttribute("sum", 0);  // Xóa giỏ hàng trong session
        }
    }


}
