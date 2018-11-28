package gov.js.service;

import gov.js.dao.AdminUserDAO;
import gov.js.dto.AdminUserDTO;

public class AdminUserService {
	
	private AdminUserDAO dao = new AdminUserDAO();
	
	// 加入一个用户，name用户姓名，phoneNum手机号，password密码，email，cityId城市id（null表示总部）
	public int addAdminUser(String name, String phoneNum, String password) {
		if(dao.getByPhoneNum(phoneNum)!=null)
		{
			throw new IllegalArgumentException("手机号为"+phoneNum+"已经存在");
		}
		
		return dao.addAdminUser(name, phoneNum, password);
	}

	// 修改用户的信息
	public void updateAdminUser(int id, String name, String password) {
		dao.updateAdminUser(id, name, password);
	}

	// 获取所有管理员
	public AdminUserDTO[] getAll() {
		return dao.getAll();
	}

	// 根据id获取DTO
	public AdminUserDTO getById(int id) {
		return dao.getById(id);
	}

	// 根据手机号获取DTO
	public AdminUserDTO getByPhoneNum(String phoneNum) {
		return dao.getByPhoneNum(phoneNum);
	}

	// 检查用户名密码是否正确
	public boolean checkLogin(String phoneNum, String password) {
		return dao.checkLogin(phoneNum, password);
	}

	// 软删除
	public void markDeleted(int adminUserId) {
		dao.markDeleted(adminUserId);
	}

	/**
	 * 判断用户是否有某个权限，比如hasPermission(3,"AdminUser.AddNew")
	 * 
	 * @param adminUserId
	 * @param permissionName
	 * @return
	 */
	public boolean hasPermission(int adminUserId, String permissionName) {
		return dao.hasPermission(adminUserId, permissionName);
	}
}
