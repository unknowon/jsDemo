package gov.js.service;

import gov.js.dao.PermissionDAO;
import gov.js.dto.PermissionDTO;

public class PermissionService {

    private PermissionDAO dao = new PermissionDAO();

    public PermissionDTO getById(int id){
        return dao.getById(id);
    }
    public PermissionDTO[] getAll(){
        return dao.getAll();
    }
    public PermissionDTO getByName(String name){
        return dao.getByName(name);
    }
    public PermissionDTO[] getByRoleId(int roleId){
        return dao.getByRoleId(roleId);
    }
    public void addPermIds(int roleId, int[] permIds){
        dao.addPermIds(roleId, permIds);
    }
    public void updatePermIds(int roleId, int[] permIds){
        dao.updatePermIds(roleId, permIds);
    }

}
