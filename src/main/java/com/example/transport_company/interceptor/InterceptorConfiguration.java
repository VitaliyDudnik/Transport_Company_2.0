package com.example.transport_company.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ManagerInterceptor managerInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private UserInterceptor userInterceptor;
    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(managerInterceptor)
                .addPathPatterns("/manager/**");
        registry
                .addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
        registry
                .addInterceptor(userInterceptor)
                .addPathPatterns("/user/**");
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/logout");
    }
}
