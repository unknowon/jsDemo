package gov.js.admin.servlet;

import gov.js.dto.CompanyDTO;
import gov.js.dto.NoticeDTO;
import gov.js.service.CompanyService;
import gov.js.service.NoticeService;
import gov.js.tools.AjaxResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Notice")
public class NoticeServlet extends BaseServlet {

    @HasPermission("Notice.Query")
    @LogMsg("查询通知公告")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoticeService noticeService = new NoticeService();
        NoticeDTO[] notices = noticeService.getAll();
        req.setAttribute("notices", notices);

        CompanyService companyService = new CompanyService();
        String[] companies = new String[notices.length];
        for(int i = 0; i < notices.length; i++){
            if(notices[i].getCompanyId() == 0){
                companies[i] = "全体";
            } else{
                CompanyDTO company = companyService.getById(notices[i].getCompanyId());
                companies[i] = company.getName();
            }
        }
        req.setAttribute("companies", companies);

        req.getRequestDispatcher("/WEB-INF/notice/noticeList.jsp").forward(req, resp);
    }

    @HasPermission("Notice.AddNew")
    public void addnew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        req.setAttribute("companyId", companyId);
        req.getRequestDispatcher("/WEB-INF/notice/noticeAdd.jsp").forward(req, resp);
    }

    @HasPermission("Notice.AddNew")
    @LogMsg("新增通知公告")
    public void addSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = Integer.parseInt(req.getParameter("companyId"));

        NoticeService noticeService = new NoticeService();
        NoticeDTO notice = new NoticeDTO();
        notice.setTitle(req.getParameter("title"));
        notice.setCompanyId(companyId);
        notice.setReadTime(null);
        notice.setContent(req.getParameter("content"));
        notice.setDesc(req.getParameter("desc"));

        noticeService.addnew(notice);

        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Notice.Delete")
    @LogMsg("删除通知公告")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        NoticeService noticeService = new NoticeService();
        noticeService.markDeleted(id);
        writeJson(resp, new AjaxResult("ok"));
    }
}
