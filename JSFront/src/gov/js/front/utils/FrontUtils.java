package gov.js.front.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.js.dto.CompanyDTO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class FrontUtils {
	private static final JedisPool jedisPool = new JedisPool("127.0.0.1");//共享同一个JedisPool
	
	public static Jedis createJedis()
	{
		return jedisPool.getResource();
	}
	
	public static void showError(HttpServletRequest req, HttpServletResponse resp, String errorMsg)
			throws ServletException, IOException {
		resp.setStatus(500);
		req.setAttribute("errorMsg", errorMsg);
		req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
	}

	/**
	 * 设置当前登录用户id
	 * 
	 * @param req
	 * @param id
	 */
	public static void setCurrentUserId(HttpServletRequest req, int id) {
		req.getSession().setAttribute("CurrentUserId", id);
	}

	/**
	 * 获取当前登录用户id（可能为null）
	 * 
	 * @param req
	 * @return
	 */
	public static int getCurrentUserId(HttpServletRequest req) {
		Integer id = (Integer) req.getSession().getAttribute("CurrentUserId");
		if(id == null){
			return -1;
		}
		return id;
	}

	public static void setCurrentCompany(HttpServletRequest req, CompanyDTO frontCompany) {
		req.getSession().setAttribute("frontCompany", frontCompany);
	}

	public static CompanyDTO getCurrentCompany(HttpServletRequest req) {
		CompanyDTO frontCompany = (CompanyDTO) req.getSession().getAttribute("frontCompany");

		return frontCompany;
	}


}
