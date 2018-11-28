package gov.js.service;

import gov.js.dao.ArticleDAO;
import gov.js.dto.ArticleDTO;

public class ArticleService {

	public void addnew(ArticleDTO article) {
		ArticleDAO dao = new ArticleDAO();
		dao.addnew(article);
	}
	
	public void markDeleted(int articleId){
		ArticleDAO dao = new ArticleDAO();
		dao.markDeleted(articleId);
	}
	
	public void update(ArticleDTO art){
		ArticleDAO dao = new ArticleDAO();
		dao.update(art);
	}
	
	public ArticleDTO getById(int articleId){
		ArticleDAO dao = new ArticleDAO();
		return dao.getById(articleId);
	}
	public ArticleDTO[] view(int typeId, int num){
		ArticleDAO dao = new ArticleDAO();
		return dao.view(typeId, num);
	}
	public ArticleDTO[] getAllByTypeId(int typeId){
		ArticleDAO dao = new ArticleDAO();
		return dao.getAllByTypeId(typeId);
	}

	public ArticleDTO[] getPagedData(int typeId, int pageSize, long currentIndex) {
		ArticleDAO dao = new ArticleDAO();




		return dao.getPagedData(typeId, pageSize, currentIndex);
	}
	public int getTotalCount(int typeId){
		ArticleDAO dao = new ArticleDAO();
		return dao.getTotalCount(typeId);
	}
}
