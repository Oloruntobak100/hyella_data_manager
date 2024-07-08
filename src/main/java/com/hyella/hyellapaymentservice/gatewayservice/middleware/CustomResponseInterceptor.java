package com.hyella.hyellapaymentservice.gatewayservice.middleware;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CustomResponseInterceptor implements HandlerInterceptor {
    @Override
    @SuppressWarnings( "null" )
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception{
        // This method is called before the controller method is invoked
        return true; // Returning true indicates that the request should proceed to the controller method
    }

    @Override
    @SuppressWarnings( "null" )
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception{
        // This method is called after the controller method is invoked, but before the view is rendered
    }

    @Override
    @SuppressWarnings( "null" )
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception{
        // This method is called after the view is rendered
        // You can modify the response here before it is sent to the client
        response.addHeader( "X-App-Version", "1.0" ); // Example of adding a custom header to the response
    }
}
