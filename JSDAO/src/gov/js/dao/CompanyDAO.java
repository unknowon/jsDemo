package gov.js.dao;

import gov.js.dao.utils.JDBCUtils;
import gov.js.dto.CompanyDTO;
import gov.js.tools.CommonUtils;
import gov.js.tools.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    public int addnew(String phoneNum, String password){
        // 生成一个盐
        String salt = VerifyCodeUtils.generateVerifyCode(6, "abcdefg1234567@!$%&*");
        String md5 = CommonUtils.calcMD5(password + salt);//用户输入的密码+盐 ，计算md5值
        try {
            return JDBCUtils.executeInsert(
                    "insert into t_companies(PhoneNum,PasswordHash,PasswordSalt,isdeleted) values(?,?,?,0)",
                    phoneNum, md5, salt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public CompanyDTO toDTO(ResultSet rs) throws SQLException {
        CompanyDTO company = new CompanyDTO();

        company.setId(rs.getInt("id"));
        company.setPhoneNum(rs.getString("phonenum"));
        company.setLocation(rs.getString("location"));
        company.setPeopleNum(rs.getInt("peoplenum"));
        company.setMaxWaterMonth(rs.getDouble("maxwatermonth"));
        company.setTel(rs.getString("tel"));
        company.setCompanyAdminName(rs.getString("companyadminname"));
        company.setPasswordHash(rs.getString("passwordhash"));
        company.setPasswordSalt(rs.getString("passwordsalt"));
        company.setName(rs.getString("name"));
        company.setDeleted(rs.getBoolean("isdeleted"));
        company.setReportTypeId(rs.getInt("reportTypeId"));

        return company;
    }
    public CompanyDTO getById(int id){
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select * from t_companies where Id=?", id);
            if (rs.next()) {
                return toDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }
    public CompanyDTO getByPhoneNum (String phoneNum){
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select * from t_companies where PhoneNum=?", phoneNum);
            if (rs.next()) {
                return toDTO(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeAll(rs);
        }
    }
    public boolean checkLogin(String phoneNum, String password){
        CompanyDTO user = getByPhoneNum(phoneNum);
        if(user==null)
        {
            return false;
        }
        String dbHash =  user.getPasswordHash();//数据库中保存的md5
        String salt = user.getPasswordSalt();
        String userHash = CommonUtils.calcMD5(password+salt);//用户密码+盐
        return dbHash.equals(userHash);
    }
    public void updatePwd(int companyId, String newPassword){
        CompanyDTO user = getById(companyId);
        if(user==null)
        {
            throw new IllegalArgumentException("给定的用户id="+companyId+"不存在！");
        }
        String salt = user.getPasswordSalt();
        String newHash = CommonUtils.calcMD5(newPassword+salt);
        try {
            JDBCUtils.executeNonQuery("Update t_companies set PasswordHash=? where Id=?", newHash,companyId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateInfo(CompanyDTO company){
        int companyId = company.getId();
        CompanyDTO user = getById(company.getId());
        if(user==null)
        {
            throw new IllegalArgumentException("给定的用户id="+companyId+"不存在！");
        }
        try {
            JDBCUtils.executeNonQuery("Update t_companies set location=?,peopleNum=?,maxWaterMonth=?,tel=?,companyAdminName=?,name=?,reporttypeid=? where Id=?",
                    company.getLocation(),company.getPeopleNum(), company.getMaxWaterMonth(), company.getTel(), company.getCompanyAdminName(),
                    company.getName(),company.getReportTypeId(),companyId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void update(String password, CompanyDTO company){
        if(StringUtils.isEmpty(password))
        {
            StringBuilder sb = new StringBuilder();
            sb.append("update t_companies set PhoneNum=?,location=?,peopleNum=?,maxwatermonth=?,tel=?,companyadminname=?,name=?,isdeleted=?\n");
            sb.append("where id=?");
            try {
                JDBCUtils.executeNonQuery(sb.toString(), company.getPhoneNum(), company.getLocation(), company.getPeopleNum(),
                        company.getMaxWaterMonth(), company.getTel(), company.getCompanyAdminName(),  company.getName(),
                        company.isDeleted());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            // 用户传进来的密码是明文，所以需要计算 散列值
            String passwordHash = CommonUtils.calcMD5(password);
            StringBuilder sb = new StringBuilder();
            sb.append("update t_companies set PhoneNum=?,location=?,peopleNum=?,maxwatermonth=?,tel=?,companyadminname=?,PasswordHash=?,PasswordSalt=?,name=?,isdeleted=?\n");
            sb.append("where id=?");
            try {
                JDBCUtils.executeNonQuery(sb.toString(), company.getPhoneNum(), company.getLocation(), company.getPeopleNum(),
                        company.getMaxWaterMonth(), company.getTel(), company.getCompanyAdminName(), company.getPasswordHash(),
                        company.getPasswordSalt(), company.getName(), company.isDeleted());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    public void markDeleted(int companyId){
        try {
            JDBCUtils.executeNonQuery("update t_companies set isdeleted=1 where Id=?", companyId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CompanyDTO[] getAll(){
        List<CompanyDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try{
            rs = JDBCUtils.executeQuery("select * from t_companies where isdeleted=0");
            while(rs.next()){
                list.add(toDTO(rs));
            }
            return list.toArray(new CompanyDTO[list.size()]);
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            JDBCUtils.closeAll(rs);
        }
    }
}
