package com.example.secondhand_trading_platform.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class TokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        // 这里可以加入你的令牌校验逻辑
        if (token == null || !isValidToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");//创建一个
            return false;
        }
        return true;
    }

    private boolean isValidToken(String token) {
        // 实现你的令牌验证逻辑
        return "valid-token".equals(token); // 示例验证逻辑
    }
}
