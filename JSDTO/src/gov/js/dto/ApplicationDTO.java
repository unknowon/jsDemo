package gov.js.dto;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationDTO {

    private int id;
    private String title;
    private int companyId;
    private String companyName;
    private String applicant;
    private Date addTime;
    private int status;
    private String content;

    private int type;
    private double water;

    private String oneContent;
    private String twoContent;

    public String getOneContent() {
        return oneContent;
    }

    public void setOneContent(String oneContent) {
        this.oneContent = oneContent;
    }

    public String getTwoContent() {
        return twoContent;
    }

    public void setTwoContent(String twoContent) {
        this.twoContent = twoContent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAddTime() {
        String value = null;
        //将Date类型的时间转换成指定格式的字符串
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        value = dateFormat.format(addTime);
        return value;
    }

    public void setAddTime(String settime) {
        //将字符串类型的日期转换成Date类型的指定格式的日期
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);//从第一个字符开始解析
        this.addTime = f.parse(settime,pos);/*对参数msg_create_date（String类型）从第一个字符开始解析（由pos），转换成java.util.Date类型，
        而这个Date的格式为"yyyy-MM-dd"（因为SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");）*/
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
