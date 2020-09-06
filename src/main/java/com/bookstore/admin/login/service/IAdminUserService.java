package com.bookstore.admin.login.service;

import com.bookstore.commons.beans.User;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/5/20 21:25
 */

public interface IAdminUserService {
    User findAdminUserByLogin(User user);
}
