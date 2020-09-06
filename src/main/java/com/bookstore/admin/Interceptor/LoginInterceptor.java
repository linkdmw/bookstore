package com.bookstore.admin.Interceptor;

import com.bookstore.commons.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/5/25 21:30
 */

public class LoginInterceptor implements HandlerInterceptor {

    //访问方法之前的预处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        StringBuffer url2 = request.getRequestURL();
        System.out.println("拦截器执行了，URL："+url2);
        //System.out.println("拦截器获取URI: "+url);
        //System.out.println("拦截器获取URL: "+url2.toString());
        if(url.endsWith("/login") || url.endsWith(".gif") || url.endsWith(".css") || url.endsWith(".png") || url.endsWith(".jpg")){
            return  true;
        }
        User loginAdminUser = (User) request.getSession().getAttribute("loginAdminUser");
        if(loginAdminUser!=null){//由于登录后台成功后把用户信息存入了新的session即loginAdminUser，因此不用判断role
            return true;
        }
        else {
            response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
            return false;
        }
    //方法编写完毕，需在spring-mvc配置文件中配置该拦截器
    }
}
