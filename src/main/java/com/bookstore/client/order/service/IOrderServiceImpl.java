package com.bookstore.client.order.service;

import com.bookstore.client.order.dao.IOrderDao;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/*
 * Created by IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/29 12:09
 */
@Service
public class IOrderServiceImpl implements IOrderService {
    @Autowired
    IOrderDao orderDao;


    @Override
    public void createOrder(Order order, Map<Product, Integer> cart) {
        //循环插入订单详细信息
        for(Product p:cart.keySet()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(p);
            orderItem.setBuynum(cart.get(p));
            orderDao.insertOrderItem(orderItem);
            //插入完毕后更新库存
            orderDao.updateProductPnum(orderItem);
        }
        //插入订单项
        orderDao.insertOrder(order);
    }

    @Override
    public void paySuccess(String order_id) {
        orderDao.updatePayState(order_id);
    }
}
