package com.bookstore.admin.login.service;

import com.bookstore.admin.login.dao.IAdminUserDao;
import com.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/5/20 21:27
 */
@Service
public class IAdminUserServiceImpl implements IAdminUserService {
    @Autowired
    IAdminUserDao adminUserDao;

    @Override
    public User findAdminUserByLogin(User user) {
        return adminUserDao.selectAdminUserByLogin(user);
    }
}
