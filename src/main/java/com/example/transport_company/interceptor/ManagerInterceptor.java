package com.example.transport_company.interceptor;

import com.example.transport_company.entity.Token;
import com.example.transport_company.RestPreconditions;
import com.example.transport_company.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class ManagerInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("X-Token");
        boolean notEmpty = RestPreconditions.checkObject(header);
        if (notEmpty) {
            UUID xToken = UUID.fromString(header);
            boolean exists = tokenService.existsToken(xToken);
            if (exists) {
                Token token = tokenService.findTokenByUUID(xToken);
                String roleName = token.getUser().getRoles().getName();
                if (roleName.equals("admin") | roleName.equals("manager")) {
                    return true;
                }
            }
        }
        response.sendError(403, "Input data is incorrect /or your access level does not match");
        return false;
    }
}
