package com.bookstore.admin.notices.dao;

import com.bookstore.commons.beans.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IAdminNoticeDao {
    List<Notice> selectNotices();

    void insertNotice(Notice notice);

    Notice selectNoticeById(Integer id);

    void updateNotice(Notice notice);

    void deleteNotice(Integer id);
}
