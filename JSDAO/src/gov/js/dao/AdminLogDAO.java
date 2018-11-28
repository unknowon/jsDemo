package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.AdminLogDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminLogDAO {
    public void addnew(int adminUserId, String message){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "insert into t_adminlogs(adminuserid,createdatetime,message)\n");
        sb.append("values(?,now(),?)\n");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            JDBCUtils.executeNonQuery(sb.toString(), adminUserId, message);
            conn.commit();
        } catch (SQLException e) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }



    public AdminLogDTO[] getAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("select log.*,u.phonenum adminUserPhoneNum,u.name adminUserName from T_adminLogs log left join t_adminUsers u on log.adminuserid=u.id\n");

        List<AdminLogDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sb.toString());
            while (rs.next()) {
                list.add(toDTO(rs));
            }
            return list.toArray(new AdminLogDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    private AdminLogDTO toDTO(ResultSet rs) throws SQLException {
        AdminLogDTO adminLog = new AdminLogDTO();

        adminLog.setId(rs.getInt("id"));
        adminLog.setAdminUserId(rs.getInt("adminUserId"));
        adminLog.setAdminUserName(rs.getString("adminUserName"));

        String date = rs.getDate("createDateTime").toString();
        String time = rs.getTime("createDateTime").toString();

        adminLog.setCreateDateTime(date + " " + time);
        adminLog.setMessage(rs.getString("message"));
        adminLog.setAdminUserPhoneNum(rs.getString("adminUserPhoneNum"));

        return adminLog;
    }


}
