package com.bookstore.admin.orders.service;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;

import java.util.List;

public interface IAdminOrderService {
    List<Order> findOrders();

    List<Order> findOrderByManyCondition(Order order);

    List<OrderItem> findOrderItemById(String id);

    void removeOrderById(String id);
}
