package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.NotResidentDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotResidentDAO {
    private NotResidentDTO toReportDTO(ResultSet rs) throws SQLException {
        NotResidentDTO dto  = new NotResidentDTO();

        dto.setId(rs.getInt("id"));
        dto.setNumber(rs.getString("number"));
        dto.setName(rs.getString("name"));
        dto.setStart(rs.getInt("start"));
        dto.setEnd(rs.getInt("end"));
        dto.setWater(rs.getInt("water"));
        dto.setMoney(rs.getDouble("money"));
        dto.setType(rs.getString("type"));
        dto.setCaliber(rs.getString("caliber"));
        dto.setRemark(rs.getString("remark"));
        dto.setLocation(rs.getString("location"));

        return dto;
    }

    public void addnew(NotResidentDTO dto){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "insert into t_notResidents(number,name,start,end,water,money,type,caliber,remark,location)\n");
        sb.append("values(?,?,?,?,?,?,?,?,?,?)");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            JDBCUtils.executeNonQuery(sb.toString(), dto.getNumber(), dto.getName(), dto.getStart(), dto.getEnd(), dto.getWater(), dto.getMoney(), dto.getType(), dto.getCaliber(),
                    dto.getRemark(), dto.getLocation());
            conn.commit();
        } catch (SQLException e) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    public NotResidentDTO[] getAll(){
        List<NotResidentDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_notResidents\n");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString());
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new NotResidentDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public NotResidentDTO getByName(String name){
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select * from t_notResidents where name=?", name);
            if (rs.next()) {
                return toReportDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public NotResidentDTO[] searchWater(int water){
        List<NotResidentDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_notResidents where water>=?\n");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(),water);
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new NotResidentDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public void updateInfo(String name, String number, int start, int end, int water, double money, String caliber, String location){

        NotResidentDTO dto = getByName(name);

        String numberTemp = dto.getNumber() + "," + number;
        int startTemp = dto.getStart() + start;
        int endTemp = dto.getEnd() + end;
        int waterTemp = dto.getWater() + water;
        double moneyTemp = dto.getMoney() + money;
        String caliberTemp = dto.getCaliber() + "," + caliber;
        String locationTemp = dto.getLocation() + "," + location;

        if(dto==null)
        {
            throw new IllegalArgumentException("给定的name:"+ name +"不存在！");
        }
        try {
            JDBCUtils.executeNonQuery("Update t_notResidents set number=?,start=?,end=?,water=?,money=?,caliber=?,location=? where id=?",
                    numberTemp, startTemp, endTemp, waterTemp, moneyTemp, caliberTemp, locationTemp, dto.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
