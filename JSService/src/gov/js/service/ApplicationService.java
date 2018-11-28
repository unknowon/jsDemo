package gov.js.service;

import gov.js.dao.ApplicationDAO;
import gov.js.dto.ApplicationDTO;

public class ApplicationService {

    private ApplicationDAO dao = new ApplicationDAO();

    public void addnew(ApplicationDTO application) {
        dao.addnew(application);
    }

    public void markLooked(int id){
        dao.markLooked(id);
    }
    public void markOK(int id){
        dao.markOK(id);
    }
    public void markCant(int id){
        dao.markCant(id);
    }
    public ApplicationDTO[] getByStatus(int status){
        return dao.getByStatus(status);
    }
    public ApplicationDTO[] getByCompanyId(int companyId){
        return dao.getByCompanyId(companyId);
    }
    public ApplicationDTO[] getAll(){
        return dao.getAll();
    }
    public ApplicationDTO getById(int id){
        return dao.getById(id);
    }
    public void setOneContent(int id, String oneContent){
        dao.setOneContent(id, oneContent);
    }
    public void setTwoContent(int id, String twoContent){
        dao.setTwoContent(id, twoContent);
    }
}
