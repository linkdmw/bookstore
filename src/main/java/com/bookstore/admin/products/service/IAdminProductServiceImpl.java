package com.bookstore.admin.products.service;

import com.bookstore.admin.products.dao.IAdminProductDao;
import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/6/3 17:41
 */
@Service
public class IAdminProductServiceImpl implements  IAdminProductService{
    @Autowired
    IAdminProductDao adminProductDao;

    //商品管理查询全部
    @Override
    public List<Product> findAllProduct() {
        return adminProductDao.selectAllProduct();
    }

    //商品管理多条件查询
    @Override
    public List<Product> findProductByManyCondition(Product product, Double maxPrice, Double minPrice) {
        Map map = new HashMap();
        map.put("product",product);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        return adminProductDao.selectProductByManyCondition(map);
    }

    //商品管理添加商品
    @Override
    public void addProduct(Product product) {
        adminProductDao.insertProduct(product);
    }

    //根据ID查找商品
    @Override
    public Product findProductById(String id) {
        return adminProductDao.selectProductById(id);
    }

    //修改商品信息
    @Override
    public void modifyProduct(Product product) {
        adminProductDao.updateProduct(product);
    }

    //根据ID删除商品
    @Override
    public void removeProductById(String id) {
        adminProductDao.deleteProduct(id);
    }

    //销售榜单
    @Override
    public List<ProductList> downloadByTime(String year, String month) {
        return adminProductDao.selectProductSalByTime(year,month);
    }
}
