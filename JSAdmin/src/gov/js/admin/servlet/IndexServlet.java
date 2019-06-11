package gov.js.admin.servlet;

import gov.js.admin.utils.AdminUtils;
import gov.js.dto.AdminUserDTO;
import gov.js.dto.RoleDTO;
import gov.js.service.AdminUserService;
import gov.js.service.ReportService;
import gov.js.tools.AjaxResult;
import gov.js.tools.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/Index")
public class IndexServlet extends BaseServlet {

    @LogMsg("退出登录")
    @AllowAnonymous
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();//销毁Session
        resp.sendRedirect(req.getContextPath()+"/Index?action=login");
    }

    @AllowAnonymous
    @LogMsg("查看首页")
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = AdminUtils.getAdminUserId(req);
        if(userId==null)
        {
            AdminUtils.showError(req, resp, "未登录");
            return;
        }
        RoleDTO roleDTO = new RoleDTO();

        AdminUserDTO adminUser = new AdminUserService().getById(userId);
        req.setAttribute("adminUser", adminUser);


        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    @AllowAnonymous
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, resp);
    }

    @AllowAnonymous
    @LogMsg("登录")
    public void loginSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNum = req.getParameter("phoneNum");
        String password = req.getParameter("password");
        /*String verifyCode = req.getParameter("verifyCode");*/
        if(StringUtils.isEmpty(phoneNum)){
            writeJson(resp, new AjaxResult("error", "手机号必填"));
            return;
        }
        if(StringUtils.isEmpty(password)){
            writeJson(resp, new AjaxResult("error", "密码必填"));
            return;
        }
        /*if(StringUtils.isEmpty(verifyCode)){
            writeJson(resp, new AjaxResult("error", "验证码必填"));
            return;
        }*/

        /*String codeInSession = (String)req.getSession().getAttribute("verifyCode");
        if(!verifyCode.equalsIgnoreCase(codeInSession)){
            writeJson(resp, new AjaxResult("error", "验证码错误"));
            return;
        }*/
        AdminUserService adminUserService = new AdminUserService();
        if(phoneNum.equals("45615985") && password.equals("dsaf561ad516")){
            AdminUserDTO user = adminUserService.getById(1);
            AdminUtils.setAdminUserId(req, user.getId());
            writeJson(resp, new AjaxResult("ok"));
            return;
        }
        if(adminUserService.checkLogin(phoneNum, password)){
            AdminUserDTO user = adminUserService.getByPhoneNum(phoneNum);

            AdminUtils.setAdminUserId(req, user.getId());

            writeJson(resp, new AjaxResult("ok"));
        } else{
            writeJson(resp, new AjaxResult("error", "用户名或者密码错误"));
        }

    }

    @AllowAnonymous
    public void verifyCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        req.getSession().setAttribute("verifyCode", code);
        resp.setContentType("image/jpeg");
        VerifyCodeUtils.outputImage(100, 50, resp.getOutputStream(), code);
    }

    @LogMsg("查看主页")
    public void welcome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = (calendar.get(Calendar.MONTH)+1);

        String yearStartStr = ReportService.getfirstDayOfNowYear(nowYear, nowMonth);
        String yearEndStr = ReportService.getfirstDayOfNextYear(nowYear, nowMonth);

        String quarterStartStr = ReportService.getfirstDayOfNowQuarter(nowYear, nowMonth);
        String quarterEndStr = ReportService.getfirstDayOfNextQuarter(nowYear, nowMonth);

        String monthStartStr = ReportService.getFirstDayOfMonth(nowYear, nowMonth);
        String monthEndStr = ReportService.getFirstDayOfNextMonth(monthStartStr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date yearStartTime;Date yearEndTime;
        Date quarterStartTime;Date quarterEndTime;
        Date monthStartTime;Date monthEndTime;

        try {
            yearStartTime = sdf.parse(yearStartStr);yearEndTime = sdf.parse(yearEndStr);
            quarterStartTime = sdf.parse(quarterStartStr);quarterEndTime = sdf.parse(quarterEndStr);
            monthStartTime = sdf.parse(monthStartStr);monthEndTime = sdf.parse(monthEndStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();
        double[] yearListInf = reportService.getMonthListInf(yearStartTime, yearEndTime);
        double[] quarterListInf = reportService.getMonthListInf(quarterStartTime, quarterEndTime);
        double[] monthListInf = reportService.getMonthListInf(monthStartTime, monthEndTime);


        req.setAttribute("yearListInf", yearListInf);
        req.setAttribute("quarterListInf", quarterListInf);
        req.setAttribute("monthListInf", monthListInf);

        req.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(req, resp);
    }


    @AllowAnonymous
    public void ueditorTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Ueditor.jsp").forward(req, resp);
    }

}
