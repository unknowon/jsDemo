package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.SettingDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingDAO {
    //设置值
    public void setValue(String name, String value){
        String oldValue = getValue(name, null);
        try
        {
            if (oldValue == null)
            {
                JDBCUtils.executeNonQuery("insert into t_settings(Name,Value) values(?,?)", name, value);
            } else
            {
                JDBCUtils.executeNonQuery("update T_Settings set Value=? where Name=?", name, value);
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    //获取值
    public String getValue(String name){
        return getValue(name,null);
    }

    /**
     * 获取值，没有就返回默认值
     * @param name
     * @param defaultValue
     * @return
     */
    public String getValue(String name, String defaultValue){
        try
        {
            String value = (String) JDBCUtils.querySingle("select Value from T_Settings where Name=?", name);
            if (value == null)
            {
                return defaultValue;
            } else
            {
                return value;
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //获得所有的
    public SettingDTO[] getAll(){
        List<SettingDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery("select * from T_Settings");
            while (rs.next())
            {
                list.add(toDTO(rs));
            }
            return list.toArray(new SettingDTO[list.size()]);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    private static SettingDTO toDTO(ResultSet rs) throws SQLException {
        SettingDTO dto = new SettingDTO();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setValue(rs.getString("value"));
        return dto;
    }

    public SettingDTO getById(int id){
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery(
                    "select * from T_settings where id=?",
                    id);
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

    public void update(String value, int id){
        try {
            JDBCUtils.executeNonQuery("Update t_settings set value=? where id=?", value, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
