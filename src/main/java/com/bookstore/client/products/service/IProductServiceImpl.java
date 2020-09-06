package com.bookstore.client.products.service;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/20 17:39
 */

import com.bookstore.client.products.dao.IProductDao;
import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class IProductServiceImpl implements IProductService {
    @Autowired
    IProductDao productDao;

    //按类别查询
    @Override
    public List<Product> findProductByCategory(String category, PageModel pageModel) {
        return productDao.selectProductByCategory(category,pageModel);
    }
    //按类别查询总数
    @Override
    public int findProductCountByCategory(String category) {
        return productDao.selectProductCountByCategory(category);
    }

    //按名称搜索
    @Override
    public List<Product> findProductByName(String productName, PageModel pageModel) {
        return productDao.selectProductByName(productName,pageModel);
    }
    //按名称搜索总数
    @Override
    public int findProductCountByName(String productName) {
        return productDao.selectProductCountByName(productName);
    }
    //按ID查询书本信息
    @Override
    public Product findProductById(String id) {
        return productDao.selectProductById(id);
    }

    //查询最新公告
    @Override
    public Notice findNoticeRecent() {
        return productDao.selectNoticeRecent();
    }

    //查询本周热卖
    @Override
    public List<Product> findWeekHotProduct() {
        return productDao.selectWeekHotProduct();
    }
}
