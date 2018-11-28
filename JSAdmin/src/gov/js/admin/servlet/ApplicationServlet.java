package gov.js.admin.servlet;

import gov.js.dto.ApplicationDTO;
import gov.js.dto.CompanyDTO;
import gov.js.service.ApplicationService;
import gov.js.service.CompanyService;
import gov.js.tools.AjaxResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Application")
public class ApplicationServlet extends BaseServlet {

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int status = Integer.parseInt(req.getParameter("status"));

        ApplicationService applicationService = new ApplicationService();
        ApplicationDTO[] applications = null;
        if(status == 0 || status == 1) {
            applications = applicationService.getByStatus(status);
        } else{
            applications = applicationService.getAll();
        }
        req.setAttribute("applications", applications);

        req.getRequestDispatcher("/WEB-INF/application/applicationList.jsp").forward(req, resp);
    }

    public void changeStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("id"));
        ApplicationService applicationService = new ApplicationService();
        ApplicationDTO application = applicationService.getById(appId);

        req.setAttribute("application", application);

        CompanyService companyService = new CompanyService();
        CompanyDTO company = companyService.getById(application.getCompanyId());
        req.setAttribute("company", company);

        req.getRequestDispatcher("/WEB-INF/application/changeStatus.jsp").forward(req, resp);
    }

    public void changeStatusSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("id"));
        String oneContent = req.getParameter("oneContent");
        System.out.println("oneContent" + oneContent);

        ApplicationService applicationService = new ApplicationService();
        int onetype = Integer.parseInt(req.getParameter("onetype"));
        if(onetype == 1){
            applicationService.markLooked(appId);
        } else{
            applicationService.markCant(appId);
        }
        applicationService.setOneContent(appId, oneContent);



        writeJson(resp, new AjaxResult("ok"));
    }


    public void changeStatus2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("id"));
        ApplicationService applicationService = new ApplicationService();
        ApplicationDTO application = applicationService.getById(appId);

        req.setAttribute("application", application);

        CompanyService companyService = new CompanyService();
        CompanyDTO company = companyService.getById(application.getCompanyId());
        req.setAttribute("company", company);

        req.getRequestDispatcher("/WEB-INF/application/changeStatus2.jsp").forward(req, resp);
    }

    public void changeStatusSubmit2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("id"));
        String twoContent = req.getParameter("twoContent");

        ApplicationService applicationService = new ApplicationService();
        int twotype = Integer.parseInt(req.getParameter("twotype"));
        if(twotype == 1){
            applicationService.markOK(appId);
        } else{
            applicationService.markCant(appId);
        }
        applicationService.setOneContent(appId, twoContent);
        writeJson(resp, new AjaxResult("ok"));
    }
}
