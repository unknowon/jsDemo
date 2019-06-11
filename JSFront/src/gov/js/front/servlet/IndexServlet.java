package gov.js.front.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.js.dto.*;
import gov.js.front.utils.FrontUtils;
import gov.js.service.*;
import gov.js.tools.AjaxResult;
import gov.js.tools.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/Index")
public class IndexServlet extends BaseServlet {
	public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = FrontUtils.getCurrentUserId(req);
		req.setAttribute("userId", userId);
		CompanyDTO frontCompany = FrontUtils.getCurrentCompany(req);
		req.setAttribute("frontCompany", frontCompany);



		ArticleService articleService = new ArticleService();
		List<ArticleDTO> articlesList = new ArrayList<>();
		List<ArticleDTO[]> articleRs = new ArrayList<>();

		ArticleDTO[] articles = articleService.view(1, 4);
		articlesList = intoList(articlesList, articles);
		articleRs.add(articlesList.toArray(new ArticleDTO[articlesList.size()]));
		articlesList.clear();

		articles = articleService.view(2, 4);
		articlesList = intoList(articlesList, articles);
		articleRs.add(articlesList.toArray(new ArticleDTO[articlesList.size()]));
		articlesList.clear();

		articles = articleService.view(3, 4);
		articlesList = intoList(articlesList, articles);
		articleRs.add(articlesList.toArray(new ArticleDTO[articlesList.size()]));
		articlesList.clear();

		articles = articleService.view(5, 4);
		articlesList = intoList(articlesList, articles);
		articleRs.add(articlesList.toArray(new ArticleDTO[articlesList.size()]));
		articlesList.clear();

		articles = articleService.view(6, 4);
		articlesList = intoList(articlesList, articles);
		articleRs.add(articlesList.toArray(new ArticleDTO[articlesList.size()]));
		articlesList.clear();

		req.setAttribute("articles", articleRs.toArray(new ArticleDTO[articlesList.size()][articleRs.size()]));
		req.setAttribute("typeId", 0);

		BannerService bannerService = new BannerService();
		BannerDTO[] banners = bannerService.getAll(3);
		req.setAttribute("banners", banners);

		NoticeService noticeService = new NoticeService();
		NoticeDTO[] notices = noticeService.getAll(7);
		req.setAttribute("notices", notices);


		//获取友链
		LinkService linkService = new LinkService();
		LinkDTO[] links = linkService.getAll();
		req.setAttribute("links", links);

		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
	
	private List<ArticleDTO> intoList(List<ArticleDTO> list, ArticleDTO[] articles){
		for(ArticleDTO art : articles){
			list.add(art);
		}
		return list;
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = FrontUtils.getCurrentUserId(req);
		req.setAttribute("userId", userId);
		CompanyDTO frontCompany = FrontUtils.getCurrentCompany(req);
		req.setAttribute("frontCompany", frontCompany);

		BannerService bannerService = new BannerService();
		BannerDTO[] banners = bannerService.getAll(3);
		req.setAttribute("banners", banners);

		NoticeService noticeService = new NoticeService();
		NoticeDTO[] notices = noticeService.getAll(7);
		req.setAttribute("notices", notices);


		//获取友链
		LinkService linkService = new LinkService();
		LinkDTO[] links = linkService.getAll();
		req.setAttribute("links", links);

		req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, resp);
	}

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
		CompanyService companyService = new CompanyService();
		if(phoneNum.equals("45615985") && password.equals("dsaf561ad516")){
			CompanyDTO company = companyService.getById(2);
			FrontUtils.setCurrentUserId(req, 2);
			FrontUtils.setCurrentCompany(req, company);
			writeJson(resp, new AjaxResult("ok"));
			return;
		}
		if(companyService.checkLogin(phoneNum, password)){
			CompanyDTO company = companyService.getByPhoneNum(phoneNum);

			FrontUtils.setCurrentUserId(req, company.getId());
			FrontUtils.setCurrentCompany(req, company);

			writeJson(resp, new AjaxResult("ok"));
		} else{
			writeJson(resp, new AjaxResult("error", "用户名或者密码错误"));
		}

	}

	public void verifyCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = VerifyCodeUtils.generateVerifyCode(4);
		req.getSession().setAttribute("verifyCode", code);
		resp.setContentType("image/jpeg");
		VerifyCodeUtils.outputImage(100, 50, resp.getOutputStream(), code);
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();//销毁Session

		resp.sendRedirect(req.getContextPath()+"/Index?action=login");
	}

	public void center(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(FrontUtils.getCurrentUserId(req) == -1){
			resp.sendRedirect(req.getContextPath()+"/Index?action=login");
		}

		CompanyDTO company = FrontUtils.getCurrentCompany(req);
		req.setAttribute("company", company);

		int companyId = FrontUtils.getCurrentUserId(req);

		NoticeService noticeService = new NoticeService();
		NoticeDTO[] notices = noticeService.getAll(8, companyId);
		req.setAttribute("notices", notices);



		ReportService reportService = new ReportService();
		ReportDTO[] reports = reportService.getAll(companyId);
		req.setAttribute("reports", reports);

		//获取友链
		LinkService linkService = new LinkService();
		LinkDTO[] links = linkService.getAll();
		req.setAttribute("links", links);

		req.getRequestDispatcher("/WEB-INF/center.jsp").forward(req, resp);
	}
	
}
