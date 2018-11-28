package gov.js.service;

import gov.js.dao.LinkDAO;
import gov.js.dto.LinkDTO;

public class LinkService {

    private LinkDAO dao = new LinkDAO();

    public void addnew(String linkName, String url){
        dao.addnew(linkName, url);
    }
    public void markDeleted(int linkId){
        dao.markDeleted(linkId);
    }
    public LinkDTO getById(int linkId){
        return dao.getById(linkId);
    }
    public LinkDTO[] getAll(){
        return dao.getAll();
    }
    public LinkDTO getByName(String linkName){
        return dao.getByName(linkName);
    }
    public void update(String linkName, String url, int id){
        dao.update(linkName, url, id);
    }

}
