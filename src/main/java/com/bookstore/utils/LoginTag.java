package com.bookstore.utils;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/20 14:39
 */

import com.bookstore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LoginTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        //获取pageContext对象
        PageContext context = (PageContext) this.getJspContext();

        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        User login_user = (User) context.getSession().getAttribute("login_user");

        if(login_user == null){                 //权限不足，重定向到权限不足界面
            response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
        }
    }
}
