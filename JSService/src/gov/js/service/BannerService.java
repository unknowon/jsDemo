package gov.js.service;

import gov.js.dao.BannerDAO;
import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.BannerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BannerService {

    private BannerDAO dao = new BannerDAO();
    public void addnew(BannerDTO banner){
        dao.addnew(banner);
    }
    //更改轮播图
    public void update(BannerDTO banner){
        dao.update(banner);
    }
    //删除轮播图
    public void markDeleted(int bannerId){
        dao.markDeleted(bannerId);
    }
    //根据id获取轮播图
    public BannerDTO getById(int bannerId){
        return dao.getById(bannerId);
    }
    public BannerDTO[] getAll(int num){
        return dao.getAll(num);
    }
}
