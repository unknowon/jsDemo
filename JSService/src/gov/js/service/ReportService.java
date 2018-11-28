package gov.js.service;

import gov.js.dao.ReportDAO;
import gov.js.dto.ReportDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReportService {

    private ReportDAO dao = new ReportDAO();

    public void addnew(ReportDTO report){
        dao.addnew(report);
    }
    public void update(ReportDTO report){
        dao.update(report);
    }
    public void markDeleted(int reportId){
        dao.markDelete(reportId);
    }

    public ReportDTO getById(int reportId){
        return dao.getById(reportId);
    }
    public ReportDTO[] getAll(int companyId){
        return dao.getAll(companyId);
    }
    public ReportDTO[] getAll(){
        return dao.getAll();
    }
    public ReportDTO[] getAll(Date startTime, Date endTime){
        return dao.getAll(startTime, endTime);
    }
    public void updateMonthWater(double monthWater, int id){
        dao.updateMonthWater(monthWater, id);
    }

    public ReportDTO[] getAll(int typeId, Date startTime, Date endTime){
        return dao.getAll(typeId, startTime, endTime);
    }

    public static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();//设置年份
        cal.set(Calendar.YEAR,year);//设置月份
        cal.set(Calendar.MONTH, month-1);//获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);//设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);//格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();//设置年份
        cal.set(Calendar.YEAR,year);//设置月份
        cal.set(Calendar.MONTH, month-1);//获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);//格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static String getFirstDayOfNextMonth(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            calendar.add(Calendar.MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ReportDTO[] getExWater(Date startTime, Date endTime){
        return dao.getExWater(startTime, endTime);
    }

    public ReportDTO[] getNowWater(Date startTime, Date endTime){
        return dao.getNowWater(startTime, endTime);
    }

    public double[] getMonthListInf(Date startTime, Date endTime){
        double[] result = new double[6];
        result[0] = dao.getWaterAll(startTime, endTime);
        result[1] = dao.getPlanWaterAll();
        result[2] = dao.getNowPlanWaterAll(startTime, endTime);
        result[3] = dao.getCompanyCount();
        result[4] = dao.getNowCompanyCount(startTime, endTime);
        result[5] = dao.getExWaterCompanyCount(startTime, endTime);
        return result;
    }

    public static String getfirstDayOfNowQuarter(int year,int month){
        if(month >=1 && month <4){
            return ""+year+"-01-01";
        }
        else if(month >= 4 && month < 7){
            return ""+year+"-04-01";
        }
        else if(month >= 7 && month <10){
            return ""+year+"-07-01";
        }
        else if(month >= 10 && month  <= 12){
            return ""+year+"-10-01";
        }
        else if(month > 12){
            return ""+(year+1)+"-01-01";
        }
        return null;
    }


    public static String getfirstDayOfNextQuarter(int year,int month){
        month += 3;
        return getfirstDayOfNowQuarter(year, month);
    }

    public static String getfirstDayOfNowYear(int year,int month){

        return ""+year+"-01-01";
    }
    public static String getfirstDayOfNextYear(int year,int month){
        year += 1;
        return getfirstDayOfNowYear(year, month);
    }

}
