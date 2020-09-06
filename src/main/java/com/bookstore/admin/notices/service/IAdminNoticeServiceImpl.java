package com.bookstore.admin.notices.service;

import com.bookstore.admin.notices.dao.IAdminNoticeDao;
import com.bookstore.commons.beans.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IAdminNoticeServiceImpl implements IAdminNoticeService {

    @Autowired
    IAdminNoticeDao adminNoticeDao;

    //查询全部
    @Override
    public List<Notice> findNotices() {
        return adminNoticeDao.selectNotices();
    }

    //添加公告
    @Override
    public void addNotice(Notice notice) {
        adminNoticeDao.insertNotice(notice);
    }

    //按ID查询
    @Override
    public Notice findNoticeById(Integer id) {
        return adminNoticeDao.selectNoticeById(id);
    }

    //更新公告
    @Override
    public void editNotice(Notice notice) {
        adminNoticeDao.updateNotice(notice);
    }

    //删除公告
    @Override
    public void removeNotice(Integer id) {
        adminNoticeDao.deleteNotice(id);
    }
}
