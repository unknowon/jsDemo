package gov.js.admin.servlet;

import gov.js.admin.utils.WcfAPI;
import gov.js.admin.utils.WcfResult;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TestScServlet extends BaseServlet {

    @AllowAnonymous
    public void testSc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WcfResult wcf = WcfAPI.GroupByCountyTotal(5224);
        req.setAttribute("wcf", wcf);

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
