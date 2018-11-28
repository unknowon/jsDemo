package gov.js.service;

import gov.js.dao.RoleDAO;
import gov.js.dto.RoleDTO;

public class RoleService {

    private RoleDAO dao = new RoleDAO();

    public int addnew(String roleName){
        return dao.addnew(roleName);
    }
    public void update(long roleId,String roleName){
        dao.update(roleId, roleName);
    }
    public void markDeleted(int roleId){
        dao.markDeleted(roleId);
    }
    public RoleDTO getById(int id){
        return dao.getById(id);
    }
    public RoleDTO[] getAll(){
        return dao.getAll();
    }
    public void addRoleIds(int adminUserId, int roleId){
        dao.addRoleIds(adminUserId, roleId);
    }
    public void updateRoleIds(int adminUserId, int roleId){
        dao.updateRoleIds(adminUserId, roleId);
    }
    public RoleDTO[] getByAdminUserId(int adminUserId){
        return dao.getByAdminUserId(adminUserId);
    }

}
