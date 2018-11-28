package gov.js.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.js.dto.AdminUserDTO;
import gov.js.dto.RoleDTO;
import gov.js.service.AdminUserService;
import gov.js.service.RoleService;
import gov.js.tools.AjaxResult;
import gov.js.tools.CommonUtils;
import org.apache.commons.lang3.StringUtils;


@WebServlet("/AdminUser")
public class AdminUserServlet extends BaseServlet {

    @HasPermission("AdminUser.Query")
    @LogMsg("查询管理员")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleService roleService = new RoleService();

        AdminUserService service = new AdminUserService();
        AdminUserDTO[] adminUsers =  service.getAll();
        req.setAttribute("adminUsers", adminUsers);

        String[] adminRoles = new String[adminUsers.length];

        for(int i=1; i <= adminUsers.length; i++) {
            RoleDTO[] userRoles = roleService.getByAdminUserId(adminUsers[i-1].getId());//用户拥有的角色
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < userRoles.length; j++) {
                sb.append(userRoles[j].getName() + " ");
            }
            adminRoles[i-1] = sb.toString();
        }

        req.setAttribute("adminRoles", adminRoles);
        req.getRequestDispatcher("/WEB-INF/adminUser/adminUserList.jsp").forward(req, resp);

    }

    @HasPermission("AdminUser.Delete")
    @LogMsg("删除管理员")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        AdminUserService service = new AdminUserService();
        service.markDeleted(id);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("AdminUser.AddNew")
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleService roleService = new RoleService();
        RoleDTO[] roles = roleService.getAll();
        req.setAttribute("roles", roles);

        req.getRequestDispatcher("/WEB-INF/adminUser/adminUserAdd.jsp").forward(req, resp);
    }

    @HasPermission("AdminUser.AddNew")
    @LogMsg("新增管理员")
    public void addSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNum = req.getParameter("phoneNum");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String roleId = req.getParameter("roleId");



        AdminUserService adminUserService = new AdminUserService();
        AdminUserDTO user = adminUserService.getByPhoneNum(phoneNum);//第一道防线：更好的展示给用户
        if(user!=null)
        {
            writeJson(resp, new AjaxResult("error","手机号已经存在",""));
            return;
        }

        //addAdminUser内部检查手机号重复则是第二道防线，防止调用addAdminUser忘了检查手机号的重复性
        int adminUserId = adminUserService.addAdminUser(name, phoneNum, password);

        RoleService roleService = new RoleService();
        roleService.addRoleIds(adminUserId, Integer.parseInt(roleId));

        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("AdminUser.Edit")
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        AdminUserService adminUserService = new AdminUserService();
        AdminUserDTO adminUser =  adminUserService.getById(id);
        req.setAttribute("adminUser", adminUser);


        RoleService roleService = new RoleService();
        RoleDTO[] roles = roleService.getAll();
        req.setAttribute("roles", roles);

        RoleDTO[] userRole = roleService.getByAdminUserId(id);//用户拥有的角色
        req.setAttribute("userRole", userRole[0]);

        req.getRequestDispatcher("/WEB-INF/adminUser/adminUserEdit.jsp").forward(req, resp);
    }

    @HasPermission("AdminUser.Edit")
    @LogMsg("修改管理员")
    public void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String roleId = req.getParameter("roleId");


        AdminUserService adminUserService = new AdminUserService();
        adminUserService.updateAdminUser(id, name, password);

        RoleService roleService = new RoleService();
        roleService.updateRoleIds(id, Integer.parseInt(roleId));

        writeJson(resp, new AjaxResult("ok"));
    }

}