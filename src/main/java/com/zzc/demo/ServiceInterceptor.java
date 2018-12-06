package com.zzc.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//定义拦截器 会对所有controller下的method进行拦截
//pre发生于method call之前

//post 只有在 preHandle 方法的返回值为true 时才能被调用。
//postHandle 方法，顾名思义就是在当前请求进行处理之后，也就是Controller 方法调用之后执行，
//但是它会在DispatcherServlet 进行视图返回渲染之前被调用,所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。

//afterCompletion:该方法也是需要当前对应的Interceptor 的preHandle 方法的返回值为true 时才会执行。
//顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
@Component
public class ServiceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("Pre Handle method is Calling");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("Post Handle method is Calling");
    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        System.out.println("Request and Response is completed");
    }
}