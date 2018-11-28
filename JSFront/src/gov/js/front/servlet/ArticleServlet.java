package gov.js.front.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.js.dto.ArticleDTO;
import gov.js.dto.LinkDTO;
import gov.js.dto.NoticeDTO;
import gov.js.front.utils.FrontUtils;
import gov.js.service.ArticleService;
import gov.js.service.LinkService;
import gov.js.service.NoticeService;
import gov.js.tools.AjaxResult;

@WebServlet("/Article")
public class ArticleServlet extends BaseServlet {

	public void articleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int typeId = Integer.parseInt(req.getParameter("typeId"));

		ArticleService articleService = new ArticleService();
		ArticleDTO[] articles = articleService.view(typeId, 5);
		req.setAttribute("articles", articles);
		req.setAttribute("typeId", typeId);

		req.getRequestDispatcher("/WEB-INF/article/article.jsp").forward(req, resp);
	}

	public void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int typeId = 11;
		int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));

		ArticleService articleService = new ArticleService();
		ArticleDTO[] articles = articleService.getPagedData(11, 5,pageIndex);
		int totalCount = articleService.getTotalCount(typeId);

		req.setAttribute("articles", articles);
		req.setAttribute("typeId", typeId);
		req.setAttribute("ctxPath", req.getContextPath());
		req.setAttribute("pageIndex", pageIndex);
		req.setAttribute("totalCount", totalCount);

		req.getRequestDispatcher("/WEB-INF/article/article.jsp").forward(req, resp);
	}

	public void articleView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		ArticleService articleService = new ArticleService();
		ArticleDTO article = articleService.getById(id);
		req.setAttribute("article", article);

		//获取友链
		LinkService linkService = new LinkService();
		LinkDTO[] links = linkService.getAll();
		req.setAttribute("links", links);

		req.getRequestDispatcher("/WEB-INF/article/articleView.jsp").forward(req, resp);
	}

	public void articlePageData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int typeId = Integer.parseInt(req.getParameter("typeId"));
		int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));

		ArticleService articleService = new ArticleService();
		ArticleDTO[] articles = articleService.getPagedData(typeId, 5,pageIndex);
		int totalCount = articleService.getTotalCount(typeId);

		req.setAttribute("articles", articles);
		req.setAttribute("typeId", typeId);
		req.setAttribute("ctxPath", req.getContextPath());
		req.setAttribute("pageIndex", pageIndex);
		req.setAttribute("totalCount", totalCount);

		//获取友链
		LinkService linkService = new LinkService();
		LinkDTO[] links = linkService.getAll();
		req.setAttribute("links", links);


		req.getRequestDispatcher("/WEB-INF/article/article.jsp").forward(req, resp);
	}

	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService noticeService = new NoticeService();
		NoticeDTO[] notices = noticeService.getAll(3);
		req.setAttribute("notices", notices);

		//获取友链
		LinkService linkService = new LinkService();
		LinkDTO[] links = linkService.getAll();
		req.setAttribute("links", links);

		req.getRequestDispatcher("/WEB-INF/article/articleAdd.jsp").forward(req, resp);
	}

	public void addSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String desc = req.getParameter("desc");
		String content = req.getParameter("content");


		ArticleDTO article = new ArticleDTO();
		article.setArticleTitle(title);
		article.setArticleContent(content);
		article.setArticleAuthor(FrontUtils.getCurrentCompany(req).getCompanyAdminName());
		article.setArticleDesc(desc);
		article.setArticleTypeId(12);

		ArticleService articleService = new ArticleService();
		articleService.addnew(article);
		writeJson(resp, new AjaxResult("ok"));
	}

}
