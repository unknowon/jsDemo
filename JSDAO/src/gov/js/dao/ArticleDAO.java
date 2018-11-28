package gov.js.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.ArticleDTO;
import org.apache.commons.lang3.StringUtils;

public class ArticleDAO {

	public void addnew(ArticleDTO article) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into T_Articles(");
		sql.append("articletitle,articledesc,articlekeywords,articleauthor,articleimg,articlecontent,articleaddtime,articleuptime,articletypeid,isDeleted");
		sql.append(") values(?,?,?,?,?,?,now(),now(),?,0)");
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			
			JDBCUtils.executeNonQuery(sql.toString(),
					article.getArticleTitle(), article.getArticleDesc(), article.getArticleKeyWords(),
					article.getArticleAuthor(), article.getArticleImg(), article.getArticleContent(),
					article.getArticleTypeId());
			conn.commit();
		} catch (SQLException e) {
			JDBCUtils.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.closeQuietly(conn);
		}
	}
	
	public void markDeleted(int articleId){
		try {
			JDBCUtils.executeNonQuery("update T_articles set Isdeleted=1 where articleId=?", articleId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(ArticleDTO art){
		StringBuilder sb = new StringBuilder();
		sb.append(
				"update T_articles set articletitle=?, articledesc=?, articlekeywords=?, articleauthor=?, \n");
		sb.append(
				"articleimg=? ,articlecontent=?, articleuptime=now()\n");
		sb.append("where articleid=?");
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);

			JDBCUtils.executeNonQuery(conn, sb.toString(), art.getArticleTitle(), art.getArticleDesc(), art.getArticleKeyWords(), art.getArticleAuthor(),
					art.getArticleImg(), art.getArticleContent(), art.getArticleId());

			conn.commit();
		} catch (SQLException ex) {
			JDBCUtils.rollback(conn);
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeQuietly(conn);
		}
	}
	
	private ArticleDTO toArticleDTO(ResultSet rs) throws SQLException {
		ArticleDTO article = new ArticleDTO();
		article.setArticleAddTime(rs.getDate("articleaddtime"));
		article.setArticleAuthor(rs.getString("articleauthor"));
		article.setArticleContent(rs.getString("articlecontent"));
		article.setArticleDesc(rs.getString("articledesc"));
		article.setArticleId(rs.getInt("articleid"));
		article.setArticleImg(rs.getString("articleimg"));
		article.setArticleKeyWords(rs.getString("articlekeywords"));
		article.setArticleTitle(rs.getString("articletitle"));
		article.setArticleTypeId(rs.getInt("articletypeid"));

		String articleTypeName = rs.getString("articleTypeName");
		if(StringUtils.isEmpty(articleTypeName)){
			article.setArticleTypeName("");
		} else{
			article.setArticleTypeName(articleTypeName);
		}

		article.setArticleTypeName(rs.getString("articletypename"));
		article.setArticleUpTime(rs.getDate("articleuptime"));
		article.setDeleted(rs.getBoolean("isdeleted"));
		return article;
	}
	
	public ArticleDTO getById(int articleId){
		ResultSet rs = null;
		try {
			rs = JDBCUtils.executeQuery(
					"select u.*,c.typename articletypename from T_articles u left join T_types c on u.articletypeid=c.typeid where u.articleid=? and u.Isdeleted=0",
					articleId);
			if (rs.next()) {
				return toArticleDTO(rs);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeAll(rs);
		}
	}
	
	public ArticleDTO[] view(int typeId, int num){
		List<ArticleDTO> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = JDBCUtils.executeQuery(
					"select u.*,c.typename articletypename from T_articles u left join T_types c on u.articletypeid=c.typeid where u.articletypeid=? and u.Isdeleted=0 order by u.articleaddtime desc limit ?",
					 typeId, num);
			while(rs.next()) {
				list.add(toArticleDTO(rs));
			}
			return list.toArray(new ArticleDTO[list.size()]);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeAll(rs);
		}
	}

	public ArticleDTO[] getAllByTypeId(int typeId){
		List<ArticleDTO> list = new ArrayList<>();
		ResultSet rs = null;
		try{
			rs = JDBCUtils.executeQuery("select u.*,c.typename articletypename from T_articles u left join T_types c on u.articletypeid=c.typeid where u.articletypeid=? and u.Isdeleted=0",
					typeId);
			while(rs.next()){
				list.add(toArticleDTO(rs));
			}
			return list.toArray(new ArticleDTO[list.size()]);
		} catch(SQLException e){
			throw new RuntimeException(e);
		} finally{
			JDBCUtils.closeAll(rs);
		}
	}

	public ArticleDTO[] getPagedData(int typeId, int pageSize, long currentIndex) {
		if (currentIndex <= 0) {
			throw new IllegalArgumentException("currentIndex必须>0");
		}
		if (pageSize <= 0) {
			throw new IllegalArgumentException("pageSize必须>0");
		}

		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append("select u.*,c.typename articletypename from T_articles u left join T_types c on u.articletypeid=c.typeid where u.articletypeid=? and u.Isdeleted=0\n");
		sbSQL.append("limit ?,?\n");

		List<ArticleDTO> articles = new ArrayList<>(pageSize);
		ResultSet rs = null;
		try {
			rs = JDBCUtils.executeQuery(sbSQL.toString(), typeId, (currentIndex - 1) * pageSize, pageSize);
			while (rs.next()) {
				articles.add(toArticleDTO(rs));
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeAll(rs);
		}
		return articles.toArray(new ArticleDTO[articles.size()]);
	}

	public int getTotalCount(int typeId){
		ResultSet rs = null;
		Number num;
		try {
			num = (Number) JDBCUtils.querySingle("select count(articleid) from T_articles where articletypeid=? and Isdeleted=0",
					typeId);

			return num.intValue();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeAll(rs);
		}
	}
}