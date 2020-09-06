package com.bookstore.admin.orders.dao;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IAdminOrderDao {
    List<Order> selectOrders();

    List<Order> selectOrderByManyCondition(Order order);

    List<OrderItem> selectOrderItemById(String id);

    void deleteOrderById(String id);

    void deleteOrderItemById(String id);
}
