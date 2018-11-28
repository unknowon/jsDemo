package gov.js.dto;


import java.util.Date;

public class ReportDTO {
    private int id;
    private int reportTypeId;
    private String content;
    private Date addTime;
    private int companyId;
    private String companyName;
    private boolean isDeleted;
    private String reportTypeName;


    private double companyMaxWaterMonth;

    private int waterType;
    private double newWater;
    private double unconvWater;
    private double reWater;
    private double reWaterRate;

    private String fileUrl;
    private String attachments;
    private double monthWater;

    public double getMonthWater() {
        return monthWater;
    }

    public void setMonthWater(double monthWater) {
        this.monthWater = monthWater;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public double getCompanyMaxWaterMonth() {
        return companyMaxWaterMonth;
    }

    public void setCompanyMaxWaterMonth(double companyMaxWaterMonth) {
        this.companyMaxWaterMonth = companyMaxWaterMonth;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getWaterType() {
        return waterType;
    }

    public void setWaterType(int waterType) {
        this.waterType = waterType;
    }

    public double getNewWater() {
        return newWater;
    }

    public void setNewWater(double newWater) {
        this.newWater = newWater;
    }

    public double getUnconvWater() {
        return unconvWater;
    }

    public void setUnconvWater(double unconvWater) {
        this.unconvWater = unconvWater;
    }

    public double getReWater() {
        return reWater;
    }

    public void setReWater(double reWater) {
        this.reWater = reWater;
    }

    public double getReWaterRate() {
        return reWaterRate;
    }

    public void setReWaterRate(double reWaterRate) {
        this.reWaterRate = reWaterRate;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(int reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
