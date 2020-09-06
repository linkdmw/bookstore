package com.bookstore.client.user.handler;

import com.bookstore.client.user.service.IUserService;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;
import com.bookstore.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/8 16:16
 */
@Controller
@RequestMapping("client/user")
public class UserHandler {
    @Autowired
    IUserService userService;

    //用户注册
    @RequestMapping("/register")
    public String register(User user, String checkCode, HttpSession session, HttpServletRequest request){
        user.setActiveCode(IdUtils.getUUID());
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //检测校验码是否正确
        if(checkCode.equals(checkCode_session)){
            int rows = userService.addUser(user,request);
            if(rows>0){
                return "redirect:/client/registersuccess.jsp";
            }else {
                request.setAttribute("fail","注册失败！");
                return "/client/register";
            }
        }else {
            request.setAttribute("check_error","校验码不正确，请重试");
            return "/client/register";
        }
    }

    //激活账户
    @RequestMapping("activeUser")
    public String activeUser(String activeCode){
        int rows = userService.activeUser(activeCode);
        if(rows>0){
            return "redirect:/client/activesuccess.jsp";
        }
        else {
            return "/client/activeFail";
        }
    }

    //ajax绑定查询邮箱是否已被注册事件
    @RequestMapping("findEmail")
    @ResponseBody
    public String findEmail(String email){
        System.out.println("注册邮箱： "+email);
        User user = userService.findEmail(email);
        System.out.println("USER: "+user);
        if(user!=null){
            return "EXIST";
        }else {
            return "OK";
        }
    }

    //ajax绑定查询用户名是否已被注册事件
    @RequestMapping("findUserName")
    @ResponseBody
    public String findUserName(String userName){
        System.out.println("注册昵称： "+userName);
        User user = userService.findUserName(userName);
        System.out.println("USER: "+user);
        if(user!=null){
            return "EXIST";
        }else {
            return "OK";
        }
    }


    //登录界面跳转
    @RequestMapping("myAccount")
    public String myAccount(HttpSession session,HttpServletRequest request){
        autoLogin(request,session);
        User user = (User) session.getAttribute("login_user");
        System.out.println("当前登录USER: "+user);
        if(user==null){     //session里没有登录用户，跳转到登录界面
            return "/client/login";
        }else {    //session里有登录用户，跳转到我的账户
            return "/client/myAccount";
        }
    }


    //自动登录实现
    private void autoLogin(HttpServletRequest request, HttpSession session) {
        User user = new User();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if("bookstore_username".equals(cookie.getName())) {
                user.setUsername( cookie.getValue());
            }
            if ("bookstore_password".equals(cookie.getName())){
                user.setPassword(cookie.getValue());
            }
        }
        user = userService.findUserByLogin(user);
        if(user!=null){
            session.setAttribute("login_user",user);
        }
        else {
            System.out.println("自动登录未成功！");
        }
    }


    //登录
    @RequestMapping("login")
    public String login(User user, String auto_login, String remember, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        user = userService.findUserByLogin(user);
        if(user==null){  //用户名或密码错误
            request.setAttribute("error","用户名或密码错误，请重新输入！");
            return "/client/login";
        }else {
            if(user.getState()==1) {        //账户密码正确且已激活
                session.setAttribute("login_user",user);
                if("1".equals(auto_login)){ //开启了自动登录，保存用户名和密码
                    addCookie(user,request,response,auto_login);
                }
                else if("1".equals(remember)){   //只开启了记住用户名
                    addCookie(user,request,response,auto_login);
                }
                return "/client/myAccount";
            }
            else {                         //账户未激活
                request.setAttribute("error","该账户还未激活，请先激活账户！");
                return "/client/login";
            }
        }
    }

    //COOKIE操作
    private void addCookie(User user, HttpServletRequest request, HttpServletResponse response,String auto_login) {
        //创建cookie对象
        Cookie cookie = new Cookie("bookstore_username",user.getUsername());
        //设定cookie的保存时间（3天）
        cookie.setMaxAge(60*60*24*3);
        //设定cookie的作用路径（当前项目路径）
        cookie.setPath(request.getContextPath()+"/");
        if("1".equals(auto_login)){
            Cookie cookie1 = new Cookie("bookstore_password",user.getPassword());
            //设定cookie1的保存时间（3天）
            cookie1.setMaxAge(60*60*24*3);
            //设定cookie1的作用路径（当前项目路径）
            cookie1.setPath(request.getContextPath()+"/");
            response.addCookie(cookie1);
        }
        response.addCookie(cookie);
    }


    //用户退出
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpSession session,HttpServletResponse response){
        session.removeAttribute("login_user");
        Cookie cookie = new Cookie("bookstore_username",null);
        Cookie cookie1 = new Cookie("bookstore_password",null);
        cookie.setMaxAge(0);
        cookie1.setMaxAge(0);
        cookie.setPath(request.getContextPath()+"/");
        cookie1.setPath(request.getContextPath()+"/");
        response.addCookie(cookie);
        response.addCookie(cookie1);
        request.setAttribute("error","用户名退出，请重新登录！");
        return "/client/login";
    }


    //修改用户信息
    @RequestMapping("modifyUser")
    public String modifyUser(User user,HttpSession session,HttpServletRequest request){
        User login_user = (User)session.getAttribute("login_user");
        user.setId(login_user.getId());
        int rows = userService.modifyUserById(user);
        if(rows>0){
            request.setAttribute("error","用户信息修改成功，请重新登录！");
            return "/client/login";
        }else {
            request.setAttribute("update_fail","用户信息修改失败！");
            return "/client/modifyuserinfo";
        }
    }


    //查询当前用户订单
    @RequestMapping("findOrderByUser")
    public String findOrderByUser(HttpSession session,Model model){
        User login_user = (User) session.getAttribute("login_user");
        List<Order> orders = userService.findOrderByUser(login_user.getId());
        model.addAttribute("orders",orders);
        return "/client/orderlist";
    }


    //根据订单id查看订单
    @RequestMapping("findOrderById")
    public String findOrderById(String id,Model model){
        List<OrderItem> items = userService.findOrderById(id);
        model.addAttribute("items",items);
        return "/client/orderInfo";
    }

    //删除用户订单
    @RequestMapping("removeOrderById")
    public String removeOrderById(String id,String flag){
        userService.removeOrderById(id,flag);
        return "redirect:/client/user/findOrderByUser";
    }

}
