package com.bookstore.commons.beans;

import lombok.Data;

/*
 * Created by IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/27 17:03
 */
@Data
public class OrderItem {
    //关联属性
    private Order order;
    private Product product;

    private int buynum;
}
