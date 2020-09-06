package com.bookstore.admin.orders.service;

import com.bookstore.admin.orders.dao.IAdminOrderDao;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IAdminOrderServiceImpl implements IAdminOrderService{

    @Autowired
    IAdminOrderDao adminOrderDao;

    //查询全部
    @Override
    public List<Order> findOrders() {
        return adminOrderDao.selectOrders();
    }

    //多条件查询
    @Override
    public List<Order> findOrderByManyCondition(Order order) {
        return adminOrderDao.selectOrderByManyCondition(order);
    }

    //按ID查询订单
    @Override
    public List<OrderItem> findOrderItemById(String id) {
        return adminOrderDao.selectOrderItemById(id);
    }

    //订单删除（订单表和订单项表）
    @Override
    public void removeOrderById(String id) {
        adminOrderDao.deleteOrderById(id);
        adminOrderDao.deleteOrderItemById(id);
    }
}
