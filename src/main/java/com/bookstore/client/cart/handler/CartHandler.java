package com.bookstore.client.cart.handler;

import com.bookstore.client.products.service.IProductService;
import com.bookstore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author: 霍梦威
 * @Date: 2020/4/27 11:43
 */
@Controller
@RequestMapping("client/cart")
public class CartHandler {
    @Autowired
    IProductService IProductService;

    //添加购物车
    @RequestMapping("addCart")
    public String addCart(String id, HttpSession session,Model model){
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        Product product = IProductService.findProductById(id);
        if(cart == null){       //session没有获取到购物车，则新建一个
            cart = new HashMap<Product,Integer>();
        }
        //.put返回的是该map原来的value值
        Integer count = cart.put(product,1);
        if(count!=null){    //已经有值，则在原来的值上加1
            cart.put(product,count+1);
            if((count+1)>product.getPnum()){
                cart.put(product,product.getPnum());
            }
        }
        session.setAttribute("cart",cart);
        return "/client/cart";

    }


    //改变加购数量
    @RequestMapping("changeCart")
    public String changeCart(String id,Integer count,HttpSession session){
        //根据id去查询要修改的图书实体
        Product product = IProductService.findProductById(id);
        //从session中取到购物车
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if(count == 0){     //如果传入的count为0，则删掉该图书所对的map
            cart.remove(product);
        }else {
            cart.put(product, count);
        }
        return "/client/cart";
    }

}
