package gov.js.service;

import gov.js.dao.CompanyDAO;
import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.CompanyDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyService {

    private CompanyDAO dao = new CompanyDAO();

    public int addnew(String phoneNum, String password){
        return dao.addnew(phoneNum, password);
    }

    public CompanyDTO getById(int id){
        return dao.getById(id);
    }
    public CompanyDTO getByPhoneNum (String phoneNum){
       return dao.getByPhoneNum(phoneNum);
    }
    public boolean checkLogin(String phoneNum, String password){
        return dao.checkLogin(phoneNum, password);
    }

    /*public void update(String password, CompanyDTO company){
        dao.update(password, company);
    }*/
    public void updateInfo(CompanyDTO company){
        dao.updateInfo(company);
    }

    public void updatePwd(int companyId, String newPassword){
        dao.updatePwd(companyId, newPassword);
    }

    public void markDeleted(int companyId){
        dao.markDeleted(companyId);
    }
    public CompanyDTO[] getAll(){
        return dao.getAll();
    }
}
