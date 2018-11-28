package gov.js.admin.servlet;

import gov.js.dao.PermissionDAO;
import gov.js.dto.PermissionDTO;
import gov.js.dto.RoleDTO;
import gov.js.service.PermissionService;
import gov.js.service.RoleService;
import gov.js.tools.AjaxResult;
import gov.js.tools.CommonUtils;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Role")
public class RoleServlet extends BaseServlet {

    @HasPermission("Role.Query")
    @LogMsg("查询角色")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleService roleService = new RoleService();
        RoleDTO[] roles =  roleService.getAll();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/WEB-INF/role/roleList.jsp").forward(req, resp);
    }

    @HasPermission("Role.Delete")
    @LogMsg("删除角色")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        RoleService service = new RoleService();
        service.markDeleted(id);
        this.writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Role.AddNew")
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PermissionDAO permDAO = new PermissionDAO();
        PermissionDTO[] perms = permDAO.getAll();
        req.setAttribute("perms", perms);
        req.getRequestDispatcher("/WEB-INF/role/roleAdd.jsp").forward(req, resp);
    }

    @HasPermission("Role.AddNew")
    @LogMsg("新增角色")
    public void addSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rolename = req.getParameter("rolename");
        String[] permIds = req.getParameterValues("permId");

        RoleService service = new RoleService();
        int roleId = service.addnew(rolename);

        PermissionService permService = new PermissionService();
        permService.addPermIds(roleId, CommonUtils.toIntArray(permIds));
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Role.Edit")
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        RoleService service = new RoleService();
        RoleDTO role = service.getById(id);
        req.setAttribute("role", role);

        //获得所有的权限项
        PermissionService permService = new PermissionService();
        PermissionDTO[] perms = permService.getAll();
        req.setAttribute("perms", perms);

        //这个角色拥有的权限项的id
        PermissionDTO[] rolePerms = permService.getByRoleId(id);
        int[] rolePermIds = new int[rolePerms.length];
        for(int i=0;i<rolePerms.length;i++)
        {
            rolePermIds[i] = rolePerms[i].getId();
        }

        req.setAttribute("rolePermIds", rolePermIds);

        req.getRequestDispatcher("/WEB-INF/role/roleEdit.jsp").forward(req, resp);
    }

    @HasPermission("Role.Edit")
    @LogMsg("修改角色")
    public void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        String rolename = req.getParameter("rolename");
        String[] permIds = req.getParameterValues("permId");

        RoleService service = new RoleService();
        service.update(id, rolename);

        PermissionService permService = new PermissionService();
        permService.updatePermIds(id, CommonUtils.toIntArray(permIds));

        writeJson(resp, new AjaxResult("ok"));
    }
}

