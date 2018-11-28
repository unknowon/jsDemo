package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.PermissionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionDAO {

    /**
     * toDTO
     * @param rs
     * @return
     * @throws SQLException
     */
    static PermissionDTO toDTO(ResultSet rs) throws SQLException
    {
        PermissionDTO dto = new PermissionDTO();
        dto.setId(rs.getInt("Id"));
        dto.setDesc(rs.getString("desc"));
        dto.setName(rs.getString("Name"));
        dto.setDeleted(rs.getBoolean("IsDeleted"));
        return dto;
    }

    /**
     * 根据id获得权限
     * @param id
     * @return
     */
    public PermissionDTO getById(int id){
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery("select * from t_permissions where Id=? and isdeleted=0", id);
            if (rs.next())
            {
                return toDTO(rs);
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

    /**
     * 获得所有权限
     * @return
     */
    public PermissionDTO[] getAll(){
        List<PermissionDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery("select * from t_permissions where  isdeleted=0");
            while (rs.next())
            {
                list.add(toDTO(rs));
            }
            return list.toArray(new PermissionDTO[list.size()]);
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        } finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 根据权限名获得权限
     * @param name
     * @return
     */
    public PermissionDTO getByName(String name){
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery("select * from t_permissions where name=? and isdeleted=0", name);
            if (rs.next())
            {
                return toDTO(rs);
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

    /**
     * 获得角色的所有权限
     * @param roleId
     * @return
     */
    public PermissionDTO[] getByRoleId(int roleId){
        List<PermissionDTO> list = new ArrayList<PermissionDTO>();
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(
                    "select * from t_permissions where  Id in(select permissionid from t_rolepermissions where roleid=?)",
                    roleId);
            while (rs.next())
            {
                list.add(toDTO(rs));
            }
            return list.toArray(new PermissionDTO[list.size()]);
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        } finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param permIds
     */
    public void addPermIds(int roleId, int[] permIds){
        try
        {
            //todo：可以优化，放到同一个connection
            for (long permId : permIds)
            {
                JDBCUtils.executeNonQuery("insert into t_rolepermissions(roleid,permissionid) values(?,?)", roleId, permId);
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 改变角色的权限， 先删后加
     * @param roleId
     * @param permIds
     */
    public void updatePermIds(int roleId, int[] permIds){
        try
        {
            JDBCUtils.executeNonQuery("delete from t_rolepermissions where roleid=?", roleId);
            addPermIds(roleId, permIds);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
