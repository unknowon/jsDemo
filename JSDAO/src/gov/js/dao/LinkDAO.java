package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.LinkDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO {
    public void addnew(String linkName, String url){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "insert into t_links(name,url)\n");
        sb.append("values(?,?)");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            JDBCUtils.executeNonQuery(sb.toString(), linkName, url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    public void update(String linkName, String url, int id){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "update t_links set name=?,url=?\n");
        sb.append("where id=?");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            JDBCUtils.executeNonQuery(sb.toString(), linkName, url, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    public void markDeleted(int linkId){
        try {
            JDBCUtils.executeNonQuery("update t_links set isdeleted=1 where Id=?", linkId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkDTO getById(int linkId){
        ResultSet rs = null;
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_links\n");
        sbsql.append("where id=? and isdeleted=0");
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(), linkId);
            if (rs.next()) {
                return toLinkDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public LinkDTO[] getAll(){
        List<LinkDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_links\n");
        sbsql.append("where isdeleted=0");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString());
            while(rs.next()) {
                list.add(toLinkDTO(rs));
            }
            return list.toArray(new LinkDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public LinkDTO getByName(String linkName){
        ResultSet rs = null;
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_links\n");
        sbsql.append("where name=? and isdeleted=0");
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(), linkName);
            if (rs.next()) {
                return toLinkDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }
    private LinkDTO toLinkDTO(ResultSet rs) throws SQLException {
        LinkDTO link  = new LinkDTO();

        link.setId(rs.getInt("id"));
        link.setName(rs.getString("name"));
        link.setUrl(rs.getString("url"));
        link.setThumbUrl(rs.getString("thumburl"));
        link.setDeleted(rs.getBoolean("isdeleted"));

        return link;
    }
}
