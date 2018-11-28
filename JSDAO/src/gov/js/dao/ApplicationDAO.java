package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.ApplicationDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    public void addnew(ApplicationDTO application) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into T_applications(");
        sql.append("title,companyid,applicant,addtime,status,content,type,water");
        sql.append(") values(?,?,?,now(),0,?,?,?)");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            JDBCUtils.executeNonQuery(sql.toString(),application.getTitle(), application.getCompanyId(), application.getApplicant(), application.getContent(),application.getType(),application.getWater());
            conn.commit();
        } catch (SQLException e) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    public void markLooked(int id){
        try {
            JDBCUtils.executeNonQuery("update T_applications set status=1 where id=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void markOK(int id){
        try {
            JDBCUtils.executeNonQuery("update T_applications set status=2 where id=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void markCant(int id){
        try {
            JDBCUtils.executeNonQuery("update T_applications set status=3 where id=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ApplicationDTO toDTO(ResultSet rs) throws SQLException {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(rs.getInt("id"));
        dto.setTitle(rs.getString("title"));
        dto.setCompanyId(rs.getInt("companyId"));
        dto.setApplicant(rs.getString("applicant"));

        String date = rs.getDate("addtime").toString();
        String time = rs.getTime("addtime").toString();
        dto.setAddTime(date + " " + time);

        dto.setStatus(rs.getInt("status"));
        dto.setContent(rs.getString("content"));
        dto.setCompanyName(rs.getString("companyName"));
        dto.setType(rs.getInt("type"));
        dto.setWater(rs.getDouble("water"));
        dto.setOneContent(rs.getString("oneContent"));
        dto.setTwoContent(rs.getString("twoContent"));
        return dto;
    }

    public ApplicationDTO[] getByStatus(int status){
        List<ApplicationDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(
                    "select u.*,c.name companyName from T_applications u left join T_companies c on u.companyid=c.id where u.status=?",
                    status);
            while(rs.next()) {
                list.add(toDTO(rs));
            }
            return list.toArray(new ApplicationDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public ApplicationDTO[] getByCompanyId(int companyId){
        List<ApplicationDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(
                    "select u.*,c.name companyName from T_applications u left join T_companies c on u.companyid=c.id where u.companyId=?",
                    companyId);
            while(rs.next()) {
                list.add(toDTO(rs));
            }
            return list.toArray(new ApplicationDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public ApplicationDTO[] getAll(){
        List<ApplicationDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select u.*,c.name companyName from T_applications u left join T_companies c on u.companyid=c.id");
            while(rs.next()) {
                list.add(toDTO(rs));
            }
            return list.toArray(new ApplicationDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public ApplicationDTO getById(int id){
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select u.*,c.name companyName from T_applications u left join T_companies c on u.companyid=c.id where u.id=?", id);
            if (rs.next()) {
                return toDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public void setOneContent(int id, String oneContent){
        try {
            JDBCUtils.executeNonQuery("update T_applications set oneContent=? where id=?", oneContent, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTwoContent(int id, String twoContent){
        try {
            JDBCUtils.executeNonQuery("update T_applications set twoContent=? where id=?", twoContent, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
