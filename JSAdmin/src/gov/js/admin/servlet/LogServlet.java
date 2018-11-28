package gov.js.admin.servlet;

import gov.js.dto.AdminLogDTO;
import gov.js.service.AdminLogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Log")
public class LogServlet extends BaseServlet {

    @HasPermission("AdminLog.Query")
    @LogMsg("查询日志")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminLogService adminLogService = new AdminLogService();
        AdminLogDTO[] logs = adminLogService.getAll();
        req.setAttribute("logs",logs);
        req.getRequestDispatcher("/WEB-INF/log/logList.jsp").forward(req, resp);
    }
}
