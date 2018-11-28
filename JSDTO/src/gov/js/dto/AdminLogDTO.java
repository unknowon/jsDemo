package gov.js.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminLogDTO {
    private int id;
    private int adminUserId;
    private Date createDateTime;
    private String message;
    private String adminUserName;
    private String adminUserPhoneNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getCreateDateTime() {
        String value = null;
        //将Date类型的时间转换成指定格式的字符串
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        value = dateFormat.format(createDateTime);
        return value;
    }

    public void setCreateDateTime(String settime) {
        //将字符串类型的日期转换成Date类型的指定格式的日期
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);//从第一个字符开始解析
        this.createDateTime = f.parse(settime,pos);/*对参数msg_create_date（String类型）从第一个字符开始解析（由pos），转换成java.util.Date类型，
        而这个Date的格式为"yyyy-MM-dd"（因为SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");）*/
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminUserPhoneNum() {
        return adminUserPhoneNum;
    }

    public void setAdminUserPhoneNum(String adminUserPhoneNum) {
        this.adminUserPhoneNum = adminUserPhoneNum;
    }
}
