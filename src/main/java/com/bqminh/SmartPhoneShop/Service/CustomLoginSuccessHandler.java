package com.bqminh.SmartPhoneShop.Service;

import com.bqminh.SmartPhoneShop.enity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    public CustomLoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        // Xác định URL redirect dựa theo role
        String targetUrl = determineTargetUrl(authentication);
        // Lưu thông tin vào session
        HttpSession session=request.getSession();
        //get email
        String email=authentication.getName();
        //query user
        User user=userService.getUserByEmail(email);
        if (user!=null){
            session.setAttribute("fullName",user.getFullName());
            session.setAttribute("avatar",user.getAvatar());
        }
        if (response.isCommitted()) {
            return;
        }
        // Chuyển hướng đến URL phù hợp
        redirectStrategy.sendRedirect(request, response, targetUrl);
        // Dọn session lỗi (nếu có)
        clearAuthenticationAttributes(request,authentication);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_USER", "/");
        roleTargetUrlMap.put("ROLE_ADMIN", "/admin");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            if (roleTargetUrlMap.containsKey(roleName)) {
                return roleTargetUrlMap.get(roleName);
            }
        }

        // Nếu không có role nào khớp, có thể đưa về trang lỗi hoặc trang mặc định
        throw new IllegalStateException("No target URL found for roles: " + authorities);
    }
    protected void clearAuthenticationAttributes(HttpServletRequest request,Authentication authentication) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
