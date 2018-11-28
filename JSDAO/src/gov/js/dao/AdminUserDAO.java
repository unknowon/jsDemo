package gov.js.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.AdminUserDTO;
import gov.js.tools.CommonUtils;

public class AdminUserDAO {

	// 加入一个用户，name用户姓名，phoneNum手机号，password密码
	public int addAdminUser(String name, String phoneNum, String password){
	
		// 用户传进来的密码是明文，所以需要计算 散列值
		String passwordHash = CommonUtils.calcMD5(password);
		StringBuilder sb = new StringBuilder();
		sb.append(
				"insert into t_adminusers(Name,PhoneNum,PasswordHash,IsDeleted,CreateDateTime)\n");
		sb.append("values(?,?,?,0,now())");
		try {
			return JDBCUtils.executeInsert(sb.toString(), name, phoneNum, passwordHash);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 修改用户的信息
	public void updateAdminUser(int id, String name, String password) {
		
		//如果密码为空，则表示用户不想修改密码，密码维持原值
		if(StringUtils.isEmpty(password))
		{
			StringBuilder sb = new StringBuilder();
			sb.append("update T_AdminUsers set Name=?\n");
			sb.append("where id=?");
			try {
				JDBCUtils.executeNonQuery(sb.toString(), name, id);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		else
		{		
			// 用户传进来的密码是明文，所以需要计算 散列值
			String passwordHash = CommonUtils.calcMD5(password);
			StringBuilder sb = new StringBuilder();
			sb.append("update T_AdminUsers set Name=?,PasswordHash=?\n");
			sb.append("where id=?");
			try {
				JDBCUtils.executeNonQuery(sb.toString(), name, passwordHash, id);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private AdminUserDTO toAdminUserDTO(ResultSet rs) throws SQLException {
		AdminUserDTO adminUser = new AdminUserDTO();
		adminUser.setId(rs.getInt("id"));
		adminUser.setName(rs.getString("name"));

		adminUser.setCreateDateTime(rs.getDate("createdatetime"));
		adminUser.setDeleted(rs.getBoolean("isdeleted"));

		adminUser.setPasswordHash(rs.getString("passwordhash"));
		adminUser.setPhoneNum(rs.getString("phonenum"));
		return adminUser;
	}

	// 获取所有管理员
	public AdminUserDTO[] getAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from T_AdminUsers where isdeleted=0\n");

		List<AdminUserDTO> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = JDBCUtils.executeQuery(sb.toString());
			while (rs.next()) {
				list.add(toAdminUserDTO(rs));
			}
			return list.toArray(new AdminUserDTO[list.size()]);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeAll(rs);
		}
	}

	// 根据id获取DTO
	public AdminUserDTO getById(int id) {
		ResultSet rs = null;
		try {
			rs = JDBCUtils.executeQuery(
					"select * from T_AdminUsers where id=? and IsDeleted=0",
					id);
			if (rs.next()) {
				return toAdminUserDTO(rs);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeAll(rs);
		}
	}

	// 根据手机号获取DTO
	public AdminUserDTO getByPhoneNum(String phoneNum) {
		ResultSet rs = null;
		try {
			rs = JDBCUtils.executeQuery(
					"select * from T_AdminUsers where PhoneNum=? and IsDeleted=0",
					phoneNum);
			if (rs.next()) {
				return toAdminUserDTO(rs);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.closeAll(rs);
		}
	}

	// 检查用户名密码是否正确
	public boolean checkLogin(String phoneNum, String password) {
		AdminUserDTO user = getByPhoneNum(phoneNum);
		if (user == null)// 手机号错误
		{
			return false;
		}

		String passwordHash = CommonUtils.calcMD5(password);

		if (user.getPasswordHash().equals(passwordHash)) {
			return true;
		} else {
			return false;
		}
		// return user.getPasswordHash().equals(passwordHash);
	}

	// 软删除
	public void markDeleted(int adminUserId) {
		try {
			JDBCUtils.executeNonQuery("Update T_AdminUsers set IsDeleted=1 where Id=?", adminUserId);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 判断用户是否有某个权限，比如hasPermission(3,"AdminUser.AddNew")
	 * 
	 * @param adminUserId
	 * @param permissionName
	 * @return
	 */
	public boolean hasPermission(int adminUserId, String permissionName) {
		// 想获得用户的拥有的角色，再获得这些角色拥有的权限，再判断permissionName这个权限是否在这些权限范围内
		//   select count(*) from t_permissions where Id in
		//   (
		//      select PermissionId from t_rolepermissions where RoleId in
		//      (
		//        select RoleId from t_adminuserroles where AdminUserId=1
		//      )
		//   )
		//   and Name ='AdminUser.Query'
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from t_permissions where Id in\n");
		sb.append("(\n");
		sb.append("   select PermissionId from t_rolepermissions where RoleId in\n");
		sb.append("   (\n");
		sb.append("      select RoleId from t_adminuserroles where AdminUserId=?\n");
		sb.append("   )\n");
		sb.append(")\n");
		sb.append("and Name =?\n");
		// 无论Integer还是Long、Double都继承自Number，这样写避免了返回值是Integer还是Long的问题
		try {
			Number number = (Number) JDBCUtils.querySingle(sb.toString(), adminUserId, permissionName);
			//如果查询的条数不是0，那么久说明有这个权限
			return number.intValue()>0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
