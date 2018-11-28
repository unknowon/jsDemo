package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.RoleDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    //新增角色
    public int addnew(String roleName){
        try {
            return JDBCUtils.executeInsert("insert into t_roles(Name,IsDeleted) values(?,0)", roleName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //更改角色
    public void update(long roleId,String roleName){
        try {
            JDBCUtils.executeNonQuery("Update t_roles set name=? where id=?", roleName,roleId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //删除角色
    public void markDeleted(int roleId){
        try {
            JDBCUtils.executeNonQuery("Update t_roles set isdeleted=1 where id=?", roleId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static RoleDTO toDTO(ResultSet rs) throws SQLException {
        RoleDTO dto = new RoleDTO();
        dto.setId(rs.getInt("Id"));
        dto.setDeleted(rs.getBoolean("IsDeleted"));
        dto.setName(rs.getString("name"));
        return dto;
    }

    //根据id获得角色
    public RoleDTO getById(int id){
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select * from t_roles where Id=? and isdeleted=0", id);
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

    //获得所有角色
    public RoleDTO[] getAll(){
        List<RoleDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select * from t_roles where  IsDeleted=0");
            while (rs.next()) {
                list.add(toDTO(rs));
            }
            return list.toArray(new RoleDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

    //为管理员添加角色
    public void addRoleIds(int adminUserId, int roleId){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            JDBCUtils.executeNonQuery(conn, "insert into t_adminuserroles(AdminUserId,RoleId) values(?,?)",
                    adminUserId, roleId);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    /**
     * 改变管理员的角色，同样是先删后加
     * @param adminUserId
     * @param roleIds
     */
    public void updateRoleIds(int adminUserId, int roleId){
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            //先删除旧的关系，再重新添加关系，这样就可以规避角色修改等
            JDBCUtils.executeNonQuery(conn,"delete from T_AdminUserRoles where AdminUserId=?", adminUserId);

            JDBCUtils.executeNonQuery(conn, "insert into T_AdminUserRoles(AdminUserId,RoleId) values(?,?)",
                    adminUserId, roleId);

            conn.commit();
        } catch (SQLException ex) {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeQuietly(conn);
        }
    }

    //获得管理员的角色
    public RoleDTO[] getByAdminUserId(int adminUserId){
        List<RoleDTO> list = new ArrayList<RoleDTO>();
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(
                    "select * from t_roles where Id in(select roleId from t_adminuserroles where AdminUserId=?)",
                    adminUserId);
            while (rs.next()) {
                list.add(toDTO(rs));
            }
            return list.toArray(new RoleDTO[list.size()]);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }

}
