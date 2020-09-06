package com.bookstore.admin.notices.service;

import com.bookstore.commons.beans.Notice;

import java.util.List;

public interface IAdminNoticeService {
    List<Notice> findNotices();

    void addNotice(Notice notice);

    Notice findNoticeById(Integer id);

    void editNotice(Notice notice);

    void removeNotice(Integer id);
}
