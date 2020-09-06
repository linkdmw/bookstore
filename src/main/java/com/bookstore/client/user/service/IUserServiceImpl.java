package com.bookstore.client.user.service;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/8 17:39
 */

import com.bookstore.client.user.dao.IUserDao;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;
import com.bookstore.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    //注册账户
    @Override
    public int addUser(User user, HttpServletRequest request) {
        String emailMsg = "点击"+"<a href='http://link666.top:8088"+request.getContextPath()
                +"/client/user/activeUser?activeCode="
                +user.getActiveCode()
                +"'>此处</a>进行激活您的账户("+user.getUsername()+")!";
        try {
            MailUtils.sendMail(user.getEmail(),emailMsg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userDao.insertUser(user);
    }

    //账户激活
    @Override
    public int activeUser(String activeCode) {
        return userDao.updateUserState(activeCode);
    }

    //ajax邮箱查询
    @Override
    public User findEmail(String email) {
        return userDao.selectEmail(email);
    }

    //ajax用户名查询s
    @Override
    public User findUserName(String userName) {
        return userDao.selectUserName(userName);
    }

    //查询当前登录用户
    @Override
    public User findUserByLogin(User user) {
        return userDao.selectUser(user);
    }

    //按ID用户修改
    @Override
    public int modifyUserById(User user) {
        return userDao.updateUserById(user);
    }

    //查询当前登录用户订单
    @Override
    public List<Order> findOrderByUser(Integer id) {
        return userDao.selectOrderByUser(id);
    }

    //根据订单ID查看订单
    @Override
    public List<OrderItem> findOrderById(String id) {
        return userDao.selectOrderItemById(id);
    }

    //根据id删除订单
    @Override
    public void removeOrderById(String id, String flag) {
        //如果flag为空，则为未付款，需加回库存
        if(flag==null){
            //加回库存
            List<OrderItem> items = userDao.selectOrderItemById(id);
            for(OrderItem item: items){
                userDao.updateProductById(item.getProduct().getId(),item.getBuynum());
            }
        }
        //是否付款都去删除订单及订单详情
        userDao.deleteOrderById(id);
        userDao.deleteOrderItemById(id);

    }
}
