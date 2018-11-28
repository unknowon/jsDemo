package gov.js.dto;

public class CompanyDTO {
    private int id;
    private String phoneNum;
    private String location;
    private int peopleNum;
    private double maxWaterMonth;
    private String tel;
    private String companyAdminName;
    private String passwordHash;
    private String passwordSalt;
    private String name;
    private boolean isDeleted;
    private int reportTypeId;

    public int getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(int reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public double getMaxWaterMonth() {
        return maxWaterMonth;
    }

    public void setMaxWaterMonth(double maxWaterMonth) {
        this.maxWaterMonth = maxWaterMonth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyAdminName() {
        return companyAdminName;
    }

    public void setCompanyAdminName(String companyAdminName) {
        this.companyAdminName = companyAdminName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
