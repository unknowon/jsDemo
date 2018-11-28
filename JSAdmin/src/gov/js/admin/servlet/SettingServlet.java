package gov.js.admin.servlet;

import gov.js.dto.SettingDTO;
import gov.js.service.SettingService;
import gov.js.tools.AjaxResult;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Setting")
public class SettingServlet extends BaseServlet {

    @HasPermission("Setting.Query")
    @LogMsg("查询系统设置")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SettingService settingService = new SettingService();
        SettingDTO[] settings = settingService.getAll();
        req.setAttribute("settings", settings);
        req.getRequestDispatcher("/WEB-INF/setting/settingList.jsp").forward(req, resp);
    }

    @HasPermission("Setting.Edit")
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        SettingService settingService = new SettingService();
        SettingDTO setting = settingService.getById(id);
        req.setAttribute("setting", setting);

        req.getRequestDispatcher("/WEB-INF/setting/settingEdit.jsp").forward(req, resp);
    }

    @HasPermission("Setting.Edit")
    @LogMsg("修改系统设置")
    public void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String value = req.getParameter("value");

        SettingService settingService = new SettingService();
        settingService.update(value, id);

        writeJson(resp, new AjaxResult("ok"));
    }
}
