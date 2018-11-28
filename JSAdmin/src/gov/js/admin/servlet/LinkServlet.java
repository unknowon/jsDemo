package gov.js.admin.servlet;

import gov.js.dao.LinkDAO;
import gov.js.dto.LinkDTO;
import gov.js.service.LinkService;
import gov.js.tools.AjaxResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Link")
public class LinkServlet extends BaseServlet {

    @HasPermission("Link.Query")
    @LogMsg("查询外链")
    public void linkList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkDAO linkDAO = new LinkDAO();
        LinkDTO[] links = linkDAO.getAll();
        req.setAttribute("links", links);
        req.getRequestDispatcher("/WEB-INF/link/linkList.jsp").forward(req, resp);
    }

    @HasPermission("Link.AddNew")
    public void linkAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/link/linkAdd.jsp").forward(req, resp);
    }

    @HasPermission("Link.AddNew")
    @LogMsg("新增外链")
    public void linkAddSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String url = req.getParameter("url");

        LinkService linkService = new LinkService();
        LinkDTO link = linkService.getByName(name);
        if(link != null){
            writeJson(resp, new AjaxResult("error", "该外链已存在", ""));
            return;
        }
        linkService.addnew(name, url);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Link.Delete")
    @LogMsg("删除外链")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        LinkService service = new LinkService();
        service.markDeleted(id);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Link.Edit")
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        LinkService linkService = new LinkService();
        LinkDTO link = linkService.getById(id);
        req.setAttribute("link",link);
        req.getRequestDispatcher("/WEB-INF/link/linkEdit.jsp").forward(req, resp);
    }

    @HasPermission("Link.Edit")
    @LogMsg("修改外链")
    public void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        LinkService linkService = new LinkService();
        linkService.update(name, url, id);
        writeJson(resp, new AjaxResult("ok"));
    }
}
