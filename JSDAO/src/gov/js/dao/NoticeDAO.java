package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.NoticeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {
    public void addnew(NoticeDTO notice){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "insert into t_notices(title,companyid,addtime,content,status,noticedesc)\n");
        sb.append("values(?,?,now(),?,0,?)\n");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            JDBCUtils.executeNonQuery(sb.toString(), notice.getTitle(), notice.getCompanyId(),notice.getContent(),notice.getDesc());
            conn.commit();
        } catch (SQLException e) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    public void markDeleted(int noticeId){
        try {
            JDBCUtils.executeNonQuery("update t_notices set status=2 where Id=?", noticeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public NoticeDTO[] getByCompanyId(int companyId){
        List<NoticeDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_notices");
        sbsql.append("where status!=2 and companyid=?");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(), companyId);
            while(rs.next()) {
                list.add(toNoticeDTO(rs));
            }
            return list.toArray(new NoticeDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }
    public NoticeDTO[] getAll(){
        List<NoticeDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_notices\n");
        sbsql.append("where status!=2");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString());
            while(rs.next()) {
                list.add(toNoticeDTO(rs));
            }
            return list.toArray(new NoticeDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }
    public void markRead(int noticeId){
        try {
            JDBCUtils.executeNonQuery("update t_notices set status=1,readtime=now() where Id=?", noticeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private NoticeDTO toNoticeDTO(ResultSet rs) throws SQLException {
        NoticeDTO notice  = new NoticeDTO();

        notice.setId(rs.getInt("id"));
        notice.setTitle(rs.getString("title"));
        notice.setCompanyId(rs.getInt("companyid"));
        notice.setAddTime(rs.getDate("addtime"));
        notice.setReadTime(rs.getDate("readtime"));
        notice.setContent(rs.getString("content"));
        notice.setStatus(rs.getInt("status"));
        notice.setDesc(rs.getString("noticedesc"));

        return notice;
    }

    public NoticeDTO[] getAll(int noticeNum){
        List<NoticeDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_notices\n");
        sbsql.append("where status!=2 and companyid=0 order by id desc limit ?\n");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(), noticeNum);
            while(rs.next()) {
                list.add(toNoticeDTO(rs));
            }
            return list.toArray(new NoticeDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }


    public NoticeDTO[] getAll(int noticeNum, int companyId){
        List<NoticeDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_notices\n");
        sbsql.append("where status!=2 and companyid=? order by id desc limit ?\n");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(),companyId, noticeNum);
            while(rs.next()) {
                list.add(toNoticeDTO(rs));
            }
            return list.toArray(new NoticeDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }


    public NoticeDTO getById(int noticeId){
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery("select * from t_notices where status!=2 and id=?", noticeId);
            if (rs.next())
            {
                return toNoticeDTO(rs);
            } else
            {
                return null;
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        } finally
        {
            JDBCUtils.closeAll(rs);
        }
    }
}
