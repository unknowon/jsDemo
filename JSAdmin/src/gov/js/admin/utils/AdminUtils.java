package gov.js.admin.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminUtils {

    public static void showError(HttpServletRequest req, HttpServletResponse resp, String errorMsg)
            throws ServletException, IOException
    {
        resp.setStatus(500);//设置响应的状态码!!!!!!!!!!!
        req.setAttribute("errorMsg", errorMsg);
        req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
    }

    public static void setAdminUserId(HttpServletRequest req,int adminUserId)
    {
        req.getSession().setAttribute("AdminUserId", adminUserId);
    }



    /**
     * 获取当前登录用户Id，如果返回null则表示取不到
     * @param req
     * @return
     */
    public static Integer getAdminUserId(HttpServletRequest req)
    {
        Integer id = (Integer) req.getSession().getAttribute("AdminUserId");
        return id;
    }


}