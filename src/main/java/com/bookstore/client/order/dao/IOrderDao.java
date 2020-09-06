package com.bookstore.client.order.dao;
/*
 * Created by IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/29 12:11
 */

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IOrderDao {

    void insertOrder(Order order);

    void insertOrderItem(OrderItem orderItem);

    void updateProductPnum(OrderItem orderItem);

    void updatePayState(String order_id);
}
