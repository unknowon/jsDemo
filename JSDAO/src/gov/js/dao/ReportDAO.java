package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.ReportDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportDAO {

    private static final String selectMainSQL;
    static {
        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append("select r.*, company.name companyname,company.maxWaterMonth companyMaxWaterMonth, type.reporttypename reporttypename\n");
        sbSQL.append("from t_reports r\n");
        sbSQL.append("left join t_companies company on r.companyid = company.id\n");
        sbSQL.append("left join t_reporttypes type on r.reporttypeid = type.id\n");
        selectMainSQL = sbSQL.toString();
    }

    //新增报表
    public void addnew(ReportDTO report){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "insert into t_reports(reporttypeid,content,addtime,companyid,waterType,newWater,unconvWater,reWater,reWaterRate,fileUrl,attachments)\n");
        sb.append("values(?,?,now(),?,?,?,?,?,?,?,?)");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            JDBCUtils.executeNonQuery(sb.toString(), report.getReportTypeId(), report.getContent(), report.getCompanyId(),
                    report.getWaterType(), report.getNewWater(), report.getUnconvWater(), report.getReWater(), report.getReWaterRate(),report.getFileUrl(),report.getAttachments());
            conn.commit();
        } catch (SQLException e) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    //软删除报表
    public void markDelete(int reportId){
        try {
            JDBCUtils.executeNonQuery("update t_reports set isdeleted=1 where Id=?", reportId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //更改报表
    public void update(ReportDTO report){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "update t_reports set reporttypeid=?, content=?, companyid=?,waterType=?,newWater=?,unconvWater=?,reWater=?,reWaterRate=?,fileUrl=?,attachments=?,monthWater=?\n");
        sb.append("where id=?");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            JDBCUtils.executeNonQuery(conn, sb.toString(), report.getReportTypeId(), report.getContent(), report.getCompanyId(),
                    report.getWaterType(), report.getNewWater(), report.getUnconvWater(), report.getReWater(), report.getReWaterRate(),report.getFileUrl(),
                    report.getAttachments(),report.getMonthWater(), report.getId());

            conn.commit();
        } catch (SQLException ex) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    private ReportDTO toReportDTO(ResultSet rs) throws SQLException {
        ReportDTO report  = new ReportDTO();

        report.setId(rs.getInt("id"));
        report.setCompanyId(rs.getInt("companyid"));
        report.setCompanyName(rs.getString("companyname"));
        report.setContent(rs.getString("content"));
        report.setReportTypeId(rs.getInt("reporttypeid"));
        report.setAddTime(rs.getDate("addtime"));
        report.setDeleted(rs.getBoolean("isdeleted"));
        report.setReportTypeName(rs.getString("reporttypename"));
        report.setWaterType(rs.getInt("waterType"));
        report.setNewWater(rs.getDouble("newWater"));
        report.setUnconvWater(rs.getDouble("unconvWater"));
        report.setReWater(rs.getDouble("reWater"));
        report.setReWaterRate(rs.getDouble("reWaterRate"));
        report.setFileUrl(rs.getString("fileUrl"));
        report.setCompanyMaxWaterMonth(rs.getDouble("companyMaxWaterMonth"));
        report.setAttachments(rs.getString("attachments"));
        report.setMonthWater(rs.getDouble("monthWater"));

        return report;
    }

    //根据id获得报表
    public ReportDTO getById(int reportId){
        ResultSet rs = null;
        StringBuilder sbsql = new StringBuilder();
        sbsql.append(selectMainSQL);
        sbsql.append("where id=? and isdeleted=0");
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(), reportId);
            if (rs.next()) {
                return toReportDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    //获得所有报表
    public ReportDTO[] getAll(){
        List<ReportDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append(selectMainSQL);
        sbsql.append("where r.isdeleted!=2");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString());
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new ReportDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    //获得某公司所有报表
    public ReportDTO[] getAll(int companyId){
        List<ReportDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append(selectMainSQL);
        sbsql.append("where companyid=? and r.isdeleted!=2\n");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(), companyId);
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new ReportDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    //获得一段时间内的报表
    public ReportDTO[] getAll(Date startTime, Date endTime){

        List<ReportDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append(selectMainSQL);
        sbsql.append("where r.isdeleted=0 and addtime >= ? and addtime <= ?");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(),startTime, endTime);
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new ReportDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }


    //获得一段时间内的某种报表
    public ReportDTO[] getAll(int typeId, Date startTime, Date endTime){

        List<ReportDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append(selectMainSQL);
        sbsql.append("where r.isdeleted=0 and addtime >= ? and addtime <= ? and r.reporttypeid=?");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(),startTime, endTime, typeId);
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new ReportDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 获得超过最大用水量的报表
     * @param startTime
     * @param endTime
     * @return
     */
    public ReportDTO[] getExWater(Date startTime, Date endTime){
        List<ReportDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append(selectMainSQL);
        sbsql.append("where r.isdeleted=0 and addtime >= ? and addtime <= ? and (r.newWater > company.maxWaterMonth)");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(),startTime, endTime);
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new ReportDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 获得已上报单位
     * @param startTime
     * @param endTime
     * @return
     */
    public ReportDTO[] getNowWater(Date startTime, Date endTime){
        List<ReportDTO> list = new ArrayList<>();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append(selectMainSQL);
        sbsql.append("where r.isdeleted=0 and addtime >= ? and addtime <= ?");
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(),startTime, endTime);
            while(rs.next()) {
                list.add(toReportDTO(rs));
            }
            return list.toArray(new ReportDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }




    /**
     * 获得总用水量(所有新用水量总和)
     * @param startTime
     * @param endTime
     * @return
     */
    public double getWaterAll(Date startTime, Date endTime){
        StringBuilder sb = new StringBuilder();
        sb.append("select sum(r.newWater) waterAll\n");
        sb.append("from t_reports r\n");
        sb.append("where r.isdeleted=0 and addtime >= ? and addtime <= ?");

        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sb.toString(), startTime, endTime);
            if (rs.next()) {
                return rs.getDouble("waterAll");
            } else {
                return 0.0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 获得计划总用水量(所有单位计划用水量总和)
     * @return
     */
    public double getPlanWaterAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("select sum(r.maxwatermonth) planWaterAll\n");
        sb.append("from t_companies r\n");
        sb.append("where r.isdeleted=0");

        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sb.toString());
            if (rs.next()) {
                return rs.getDouble("planWaterAll");
            } else {
                return 0.0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 获得已上报的总计划用水量
     * @param startTime
     * @param endTime
     * @return
     */
    public double getNowPlanWaterAll(Date startTime, Date endTime){
        StringBuilder sb = new StringBuilder();
        sb.append("select sum(maxwatermonth) nowPlanWaterAll from t_companies where id in\n");
        sb.append("(select companyid from t_reports as r \n");
        sb.append("where isdeleted=0 and addtime >= ? and addtime <= ?)");

        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sb.toString(), startTime, endTime);
            if (rs.next()) {
                return rs.getDouble("nowPlanWaterAll");
            } else {
                return 0.0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 获得单位总数
     * @return
     */
    public int getCompanyCount(){
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select count(id) companyCount from t_companies where isdeleted=0");
            if (rs.next()) {
                return rs.getInt("companyCount");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 获得已上报的公司数
     * @param startTime
     * @param endTime
     * @return
     */
    public int getNowCompanyCount(Date startTime, Date endTime){
        StringBuilder sb = new StringBuilder();
        sb.append("select count(id) nowCompanyCount from t_companies where id in\n");
        sb.append("(select companyid from t_reports as r\n");
        sb.append("where isdeleted=0 and addtime >= ? and addtime <= ?)");

        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sb.toString(), startTime, endTime);
            if (rs.next()) {
                return rs.getInt("nowCompanyCount");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 获得超过最大用水量的单位数量
     * @param startTime
     * @param endTime
     * @return
     */
    public int getExWaterCompanyCount(Date startTime, Date endTime){
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct count(company.id) exWaterCompanyCount\n");
        sb.append("from t_reports r\n");
        sb.append("left join t_companies company on r.companyid = company.id\n");
        sb.append("left join t_reporttypes type on r.reporttypeid = type.id\n");
        sb.append("where r.isdeleted=0 and  (r.newWater > company.maxWaterMonth) and addtime >= ? and addtime <= ?\n");

        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(sb.toString(), startTime, endTime);
            if (rs.next()) {
                return rs.getInt("exWaterCompanyCount");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    public void updateMonthWater(double monthWater, int id){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "update t_reports set monthWater=?\n");
        sb.append("where id=?");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            JDBCUtils.executeNonQuery(conn, sb.toString(), monthWater, id);

            conn.commit();
        } catch (SQLException ex) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }
}
