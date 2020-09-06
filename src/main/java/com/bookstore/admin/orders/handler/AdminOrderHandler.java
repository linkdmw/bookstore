package com.bookstore.admin.orders.handler;

import com.bookstore.admin.orders.service.IAdminOrderService;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/orders")
public class AdminOrderHandler {

    @Autowired
    IAdminOrderService adminOrderService;

    //订单管理——查询订单
    @RequestMapping("findOrders")
    public String findOrders(Model model){
        List<Order> orders = adminOrderService.findOrders();

        model.addAttribute("orders",orders);
        return "/admin/orders/list";
    }

    //订单管理——多条件查询订单
    @RequestMapping("findOrderByManyCondition")
    public String findOrderByManyCondition(Order order,Model model){
        List<Order> orders = adminOrderService.findOrderByManyCondition(order);

        model.addAttribute("orders",orders);
        model.addAttribute("order",order);
        return "/admin/orders/list";
    }

    //订单管理——查询订单详情
    @RequestMapping("findOrderById")
    public String findOrderById(String id, Model model){
        List<OrderItem> orderItems = adminOrderService.findOrderItemById(id);

        model.addAttribute("orderItems",orderItems);
        return "/admin/orders/view";
    }

    //订单管理——删除订单
    @RequestMapping("removeOrderById")
    public String removeOrderById(String id){

        adminOrderService.removeOrderById(id);
        return "redirect:/admin/orders/findOrders";
    }
}
