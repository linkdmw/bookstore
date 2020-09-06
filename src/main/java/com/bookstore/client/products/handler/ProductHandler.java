package com.bookstore.client.products.handler;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/20 17:07
 */

import com.bookstore.client.products.service.IProductService;
import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("client/product")
public class ProductHandler {

    @Autowired
    IProductService productService;


    //按类别查询
    @RequestMapping("findProductByCategory")
    public String findProductByCategory(@RequestParam(defaultValue = "1") int pageIndex, String category, Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        //按类别查询
        List<Product> products = productService.findProductByCategory(category,pageModel);
        //查询该类别总数，放入pageModel
        int totalCount = productService.findProductCountByCategory(category);
        pageModel.setRecordCount(totalCount);

        model.addAttribute("products",products);
        model.addAttribute("category",category);
        model.addAttribute("pageModel",pageModel);
        return "/client/product_list";
    }


    //按名称搜索
    @RequestMapping("findProductByName")
    public String findProductByName(@RequestParam(defaultValue = "1")int pageIndex, String productName,Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Product> products = productService.findProductByName(productName,pageModel);

        int totalCount = productService.findProductCountByName(productName);
        pageModel.setRecordCount(totalCount);
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("productName",productName);
        return "/client/product_search_list";
    }


    //查询图书详细信息
    @RequestMapping("findProductById")
    public String findProductById(String id,Model model){
        Product product = productService.findProductById(id);
        model.addAttribute("p",product);
        return "/client/info";
    }

    //跳转首页前处理
    @RequestMapping("showIndex")
    public String showIndex(Model model){
        System.out.println("showIndex执行了！");
        Notice notice = productService.findNoticeRecent();
        model.addAttribute("notice",notice);
        List<Product> products = productService.findWeekHotProduct();
        model.addAttribute("products",products);
        return "/client/index";
    }
}
