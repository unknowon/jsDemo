package gov.js.front.servlet;

import gov.js.dto.NoticeDTO;
import gov.js.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Notice")
public class NoticeServlet extends BaseServlet {

    //阅读通知
    public void noticeView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int noticeId = Integer.parseInt(req.getParameter("noticeId"));

        NoticeService noticeService = new NoticeService();
        NoticeDTO notice = noticeService.getById(noticeId);
        if(notice.getStatus() == 0){
            noticeService.markRead(noticeId);
        }
        req.setAttribute("notice", notice);
        req.getRequestDispatcher("/WEB-INF/notice/noticeView.jsp").forward(req, resp);
    }
}
