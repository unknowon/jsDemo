package gov.js.dto;

import java.util.Date;

public class ArticleDTO {
	private int articleId;//文章id
	private String articleTitle;//文章标题
	private String articleDesc;//文章描述
	private String articleKeyWords;//文章关键词
	private String articleAuthor;//文章作者
	private String articleImg;//文章缩略图
	private String articleContent;//文章内容
	private Date articleAddTime;//文章添加时间
	private Date articleUpTime;//文章跟新时间
	private boolean isDeleted;//是否删除
	
	private int articleTypeId;//文章类型 ids
	private String articleTypeName;//文章类型名
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleDesc() {
		return articleDesc;
	}
	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}
	public String getArticleKeyWords() {
		return articleKeyWords;
	}
	public void setArticleKeyWords(String articleKeyWords) {
		this.articleKeyWords = articleKeyWords;
	}
	public String getArticleAuthor() {
		return articleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	public String getArticleImg() {
		return articleImg;
	}
	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public Date getArticleAddTime() {
		return articleAddTime;
	}
	public void setArticleAddTime(Date articleAddTime) {
		this.articleAddTime = articleAddTime;
	}
	public Date getArticleUpTime() {
		return articleUpTime;
	}
	public void setArticleUpTime(Date articleUpTime) {
		this.articleUpTime = articleUpTime;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(int articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}
}
