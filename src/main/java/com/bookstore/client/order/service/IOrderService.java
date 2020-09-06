package com.bookstore.client.order.service;
/*
 * Created by IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/29 12:08
 */

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.Product;

import java.util.Map;

public interface IOrderService {
    void createOrder(Order order, Map<Product, Integer> cart);

    void paySuccess(String order_id);
}
