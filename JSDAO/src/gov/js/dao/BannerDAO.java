package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.BannerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BannerDAO {
    //新建轮播图
    public void addnew(BannerDTO banner){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "insert into t_banners(bannerurl,bannerdesc,isdeleted)\n");
        sb.append("values(?,?,?)");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            JDBCUtils.executeNonQuery(sb.toString(), banner.getBannerUrl(),
                    banner.getBannerDesc(), banner.isDeleted());
            conn.commit();
        } catch (SQLException e) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }
    //更改轮播图
    public void update(BannerDTO banner){
        StringBuilder sb = new StringBuilder();
        sb.append(
                "update t_banners set bannerurl=?, bannerdesc=?\n");
        sb.append("where id=?");
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            JDBCUtils.executeNonQuery(conn, sb.toString(), banner.getId(), banner.getBannerUrl(), banner.getBannerDesc());

            conn.commit();
        } catch (SQLException ex) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }
    //删除轮播图
    public void markDeleted(int bannerId){
        try {
            JDBCUtils.executeNonQuery("update t_banners set isdeleted=1 where Id=?", bannerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //根据id获取轮播图
    public BannerDTO getById(int bannerId){
        ResultSet rs = null;
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select * from t_banners\n");
        sbsql.append("where id=? and isdeleted=0");
        try {
            rs = JDBCUtils.executeQuery(sbsql.toString(), bannerId);
            if (rs.next()) {
                return toBannerDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    private BannerDTO toBannerDTO(ResultSet rs) throws SQLException {
        BannerDTO banner = new BannerDTO();

        banner.setId(rs.getInt("id"));
        banner.setDeleted(rs.getBoolean("isdeleted"));
        banner.setBannerDesc(rs.getString("bannerdesc"));
        banner.setBannerUrl(rs.getString("bannerurl"));

        return banner;
    }

    public BannerDTO[] getAll(){
        List<BannerDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try{
            rs = JDBCUtils.executeQuery("select * from t_banners where isdeleted=0");
            while(rs.next()){
                list.add(toBannerDTO(rs));
            }
            return list.toArray(new BannerDTO[list.size()]);
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            JDBCUtils.closeAll(rs);
        }
    }

    public BannerDTO[] getAll(int num){
        List<BannerDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try{
            rs = JDBCUtils.executeQuery("select * from t_banners where isdeleted=0 order by id desc limit ?", num);
            while(rs.next()){
                list.add(toBannerDTO(rs));
            }
            return list.toArray(new BannerDTO[list.size()]);
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            JDBCUtils.closeAll(rs);
        }
    }

}
