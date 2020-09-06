package com.bookstore.admin.login.handler;

import com.bookstore.admin.login.service.IAdminUserService;
import com.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/5/20 18:39
 */
@Controller
@RequestMapping("admin/login")
public class AdminUserHandler {

    @Autowired
    IAdminUserService adminUserService;

    //跳转到登录界面
    @RequestMapping("toLogin")
    public String toAdminLogin(){
        return "/admin/login/login";
    }

    //管理员登录
    @RequestMapping("login")
    public String login(User user, HttpSession session, Model model){
        User loginAdminUser = adminUserService.findAdminUserByLogin(user);

        if(loginAdminUser!=null){
            if(loginAdminUser.getRole().equals("管理员")){
                //角色为管理员
                session.setAttribute("loginAdminUser",loginAdminUser);
                return "/admin/login/home";
            }
            else {
                //非管理员跳转到权限不足界面
                return "/admin/error/privilege";
            }

        }
        else {
            model.addAttribute("login_fail","管理员用户名或密码错误，请重新登录");
            return "/admin/login/login";
        }
    }


    @RequestMapping("logout")
    public String logout(HttpSession session,Model model){
        session.removeAttribute("loginAdminUser");
        model.addAttribute("login_fail","您已退出登录！");
        return "/admin/login/login";
    }
}
