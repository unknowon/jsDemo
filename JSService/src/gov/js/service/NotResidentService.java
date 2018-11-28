package gov.js.service;

import gov.js.dao.NotResidentDAO;
import gov.js.dto.NotResidentDTO;

public class NotResidentService {
    private NotResidentDAO dao = new NotResidentDAO();

    public void addnew(NotResidentDTO dto){
        dao.addnew(dto);
    }
    public NotResidentDTO[] getAll(){
        return dao.getAll();
    }
    public NotResidentDTO getByName(String name){
        return dao.getByName(name);
    }
    public void updateInfo(String name, String number, int start, int end, int water, double money, String caliber, String location){
        dao.updateInfo(name, number, start, end, water, money, caliber, location);
    }
    public NotResidentDTO[] searchWater(int water){
        return dao.searchWater(water);
    }
}
