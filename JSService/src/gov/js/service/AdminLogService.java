package gov.js.service;

import gov.js.dao.AdminLogDAO;
import gov.js.dto.AdminLogDTO;

public class AdminLogService {

    private AdminLogDAO dao = new AdminLogDAO();

    public void addnew(int adminUserId, String message){
        dao.addnew(adminUserId, message);
    }
    public AdminLogDTO[] getAll() {
        return dao.getAll();
    }


}
