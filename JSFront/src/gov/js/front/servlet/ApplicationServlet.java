package gov.js.front.servlet;

import gov.js.dto.ApplicationDTO;
import gov.js.dto.LinkDTO;
import gov.js.dto.NoticeDTO;
import gov.js.front.utils.FrontUtils;
import gov.js.service.ApplicationService;
import gov.js.service.LinkService;
import gov.js.service.NoticeService;
import gov.js.tools.AjaxResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Application")
public class ApplicationServlet extends BaseServlet {

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoticeService noticeService = new NoticeService();
        NoticeDTO[] notices = noticeService.getAll(3);
        req.setAttribute("notices", notices);

        //获取友链
        LinkService linkService = new LinkService();
        LinkDTO[] links = linkService.getAll();
        req.setAttribute("links", links);

        req.getRequestDispatcher("/WEB-INF/application/applicationAdd.jsp").forward(req, resp);
    }

    public void addSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationService applicationService = new ApplicationService();
        ApplicationDTO application = new ApplicationDTO();


        String title = req.getParameter("title");
        int companyId = FrontUtils.getCurrentCompany(req).getId();
        String companyName = FrontUtils.getCurrentCompany(req).getName();
        String applicant = FrontUtils.getCurrentCompany(req).getCompanyAdminName();
        String content = req.getParameter("content");
        System.out.println(content);
        int type = Integer.parseInt(req.getParameter("type"));
        if(type == 1 || type == 2) {
            double water = Double.parseDouble(req.getParameter("water"));
            application.setWater(water);
        }

        application.setTitle(title);
        application.setCompanyId(companyId);
        application.setCompanyName(companyName);
        application.setApplicant(applicant);
        application.setContent(content);
        application.setType(type);


        applicationService.addnew(application);

        writeJson(resp, new AjaxResult("ok"));
    }
}
