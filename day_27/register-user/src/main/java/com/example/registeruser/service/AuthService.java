package com.example.registeruser.service;

import com.example.registeruser.exception.NotFoundException;
import com.example.registeruser.request.LoginRequest;
import com.example.registeruser.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(LoginRequest request, HttpSession session) {
        try {
            // 1. Tạo đối tượng xác thực
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            // 2. Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // 3. Lưu đối tượng vào trong context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 4. Set cookie
            session.setAttribute("MY_SESSION", authentication.getName());

            return "Đăng nhập thành công";
        }
        catch (AuthenticationException e) {
            throw new NotFoundException("Tài khoản hoặc mật khẩu không chính xác");
        }
    }

    public String logout(HttpSession session) {
        session.invalidate();
        return "Đăng xuất thành công";
    }

    public String register(RegisterRequest request) {
        return null;
    }

    public String confirm(String token) {
        return null;
    }
}
