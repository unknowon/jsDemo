package gov.js.service;

import gov.js.dao.SettingDAO;
import gov.js.dto.SettingDTO;

public class SettingService {

    private SettingDAO dao = new SettingDAO();

    public void setValue(String name, String value){
        dao.setValue(name, value);
    }
    public String getValue(String name){
        return dao.getValue(name);
    }
    public String getValue(String name, String defaultValue){
        return dao.getValue(name, defaultValue);
    }
    public SettingDTO[] getAll(){
        return dao.getAll();
    }
    public SettingDTO getById(int id){
        return dao.getById(id);
    }
    public void update(String value, int id){
        dao.update(value, id);
    }

}
