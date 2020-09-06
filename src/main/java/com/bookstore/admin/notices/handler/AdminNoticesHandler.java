package com.bookstore.admin.notices.handler;

import com.bookstore.admin.notices.service.IAdminNoticeService;
import com.bookstore.commons.beans.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/notices")
public class AdminNoticesHandler {

    @Autowired
    IAdminNoticeService adminNoticeService;

    //公告管理——查询所有
    @RequestMapping("findNotices")
    public String findNotices(Model model){
        List<Notice> notices = adminNoticeService.findNotices();
        model.addAttribute("notices",notices);
        return "/admin/notices/list";
    }

    //公告管理——添加公告
    @RequestMapping("addNotice")
    public String addNotice(Notice notice){
        adminNoticeService.addNotice(notice);
        return "redirect:/admin/notices/findNotices";
    }

    //公告管理——按ID查询公告
    @RequestMapping("findNoticeById")
    public String findNoticeById(Integer id,Model model){
        Notice notice = adminNoticeService.findNoticeById(id);
        model.addAttribute("n",notice);
        return "/admin/notices/edit";
    }

    //公告管理——修改公告
    @RequestMapping("editNotice")
    public String editNotice(Notice notice){
        adminNoticeService.editNotice(notice);
        return "redirect:/admin/notices/findNotices";
    }

    //公告管理——删除公告
    @RequestMapping("removeNotice")
    public String removeNotice(Integer id){
        adminNoticeService.removeNotice(id);
        return "redirect:/admin/notices/findNotices";
    }
}
