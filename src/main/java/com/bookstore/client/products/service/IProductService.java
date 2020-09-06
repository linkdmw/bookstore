package com.bookstore.client.products.service;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/20 17:38
 */

import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;

import java.util.List;

public interface IProductService {
    List<Product> findProductByCategory(String category, PageModel pageModel);

    int findProductCountByCategory(String category);

    List<Product> findProductByName(String productName, PageModel pageModel);

    int findProductCountByName(String productName);

    Product findProductById(String id);

    Notice findNoticeRecent();

    List<Product> findWeekHotProduct();
}
