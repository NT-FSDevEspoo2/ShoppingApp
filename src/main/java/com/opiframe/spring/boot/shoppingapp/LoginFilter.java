/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp;

import com.opiframe.spring.boot.shoppingapp.users.service.UserService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author O
 */
@Component
public class LoginFilter implements Filter {

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean loginFilterRegistration(LoginFilter loginFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(loginFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setName("LoginFilter");
        filterRegistrationBean.setAsyncSupported(true);
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("token");
        if (token != null && userService.isUserLogged(token)) {
            filterChain.doFilter(request, response);
        } else {
            httpResponse.sendError(403);
        }
    }

}
