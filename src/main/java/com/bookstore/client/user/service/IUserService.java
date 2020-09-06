package com.bookstore.client.user.service;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/8 17:38
 */

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    int addUser(User user, HttpServletRequest request);

    int activeUser(String activeCode);

    User findEmail(String email);

    User findUserName(String userName);

    User findUserByLogin(User user);

    int modifyUserById(User user);

    List<Order> findOrderByUser(Integer id);

    List<OrderItem> findOrderById(String id);

    void removeOrderById(String id, String flag);
}
