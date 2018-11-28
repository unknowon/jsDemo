package gov.js.service;

import gov.js.dao.NoticeDAO;
import gov.js.dto.NoticeDTO;

public class NoticeService {

    private NoticeDAO dao = new NoticeDAO();

    public void addnew(NoticeDTO notice){
        dao.addnew(notice);
    }
    public void markDeleted(int noticeId){
        dao.markDeleted(noticeId);
    }
    public NoticeDTO[] getByCompanyId(int companyId){
        return dao.getByCompanyId(companyId);
    }
    public NoticeDTO[] getAll(){
        return dao.getAll();
    }
    public void markRead(int noticeId){
        dao.markRead(noticeId);
    }
    public NoticeDTO[] getAll(int noticeNum){
        return dao.getAll(noticeNum);
    }
    public NoticeDTO getById(int noticeId){
        return dao.getById(noticeId);
    }
    public NoticeDTO[] getAll(int noticeNum, int companyId){
        return dao.getAll(noticeNum, companyId);
    }

}
