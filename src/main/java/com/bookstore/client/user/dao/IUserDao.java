package com.bookstore.client.user.dao;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/8 17:40
 */

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IUserDao {
    int insertUser(User user);

    int updateUserState(String activeCode);

    User selectEmail(String email);

    User selectUserName(String userName);

    User selectUser(User user);

    int updateUserById(User user);

    List<Order> selectOrderByUser(Integer id);

    List<OrderItem> selectOrderItemById(String id);

    void updateProductById(@Param("id") String id, @Param("buyNum") int buyNum);

    void deleteOrderById(String id);

    void deleteOrderItemById(String id);
}
