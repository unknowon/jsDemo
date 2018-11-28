package gov.js.admin.servlet;

import gov.js.dao.NotResidentDAO;
import gov.js.dto.CompanyDTO;
import gov.js.dto.NotResidentDTO;
import gov.js.dto.ReportDTO;
import gov.js.service.CompanyService;
import gov.js.service.NotResidentService;
import gov.js.service.ReportService;
import gov.js.tools.AjaxResult;
import gov.js.tools.CommonUtils;
import gov.js.tools.VerifyCodeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Report")
@MultipartConfig
public class ReportServlet extends BaseServlet {

    @HasPermission("Report.Query")
    @LogMsg("查询报表")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = (calendar.get(Calendar.MONTH)+1);

        String startStr = ReportService.getFirstDayOfMonth(nowYear, nowMonth);
        String endStr = ReportService.getFirstDayOfNextMonth(startStr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();

        ReportDTO[] reports = reportService.getAll(startTime, endTime);
        for (int i = 0; i < reports.length; i++) {
            String[] contents = reports[i].getContent().split("\\|");
            reports[i].setContent(contents[contents.length-1]);
        }

        req.setAttribute("reports", reports);
        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);

        req.getRequestDispatcher("/WEB-INF/report/reportList.jsp").forward(req,resp);
    }


    public void monthList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = (calendar.get(Calendar.MONTH)+1);

        String startStr = ReportService.getFirstDayOfMonth(nowYear, nowMonth);
        String endStr = ReportService.getFirstDayOfNextMonth(startStr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();
        double[] listInf = reportService.getMonthListInf(startTime, endTime);

        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);
        req.setAttribute("listInf", listInf);

        req.getRequestDispatcher("/WEB-INF/report/reportMonthList.jsp").forward(req,resp);
    }

    public void quarterList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = (calendar.get(Calendar.MONTH)+1);

        String startStr = ReportService.getfirstDayOfNowQuarter(nowYear, nowMonth);
        String endStr = ReportService.getfirstDayOfNextQuarter(nowYear, nowMonth);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();
        double[] listInf = reportService.getMonthListInf(startTime, endTime);

        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);
        req.setAttribute("listInf", listInf);

        req.getRequestDispatcher("/WEB-INF/report/reportQuarterList.jsp").forward(req,resp);
    }

    public void yearList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = (calendar.get(Calendar.MONTH)+1);

        String startStr = ReportService.getfirstDayOfNowYear(nowYear, nowMonth);
        String endStr = ReportService.getfirstDayOfNextYear(nowYear, nowMonth);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();
        double[] listInf = reportService.getMonthListInf(startTime, endTime);

        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);
        req.setAttribute("listInf", listInf);

        req.getRequestDispatcher("/WEB-INF/report/reportYearList.jsp").forward(req,resp);
    }

    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String startStr = req.getParameter("startTime");
        String endStr = req.getParameter("endTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();

        ReportDTO[] reports = reportService.getAll(startTime, endTime);

        req.setAttribute("reports", reports);
        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);

        req.getRequestDispatcher("/WEB-INF/report/reportList.jsp").forward(req,resp);
    }

    public void searchMonthList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String startStr = req.getParameter("startTime");
        String endStr = req.getParameter("endTime");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();
        double[] listInf = reportService.getMonthListInf(startTime, endTime);

        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);
        req.setAttribute("listInf", listInf);

        req.getRequestDispatcher("/WEB-INF/report/reportMonthList.jsp").forward(req,resp);
    }



    @HasPermission("Report.Delete")
    @LogMsg("删除报表")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        ReportService reportService = new ReportService();
        reportService.markDeleted(id);

        writeJson(resp, new AjaxResult("ok"));
    }

    public void downloadMonthReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();
        String fileNameMd5 = CommonUtils.calcMD5(VerifyCodeUtils.generateVerifyCode(6));
        String rootDir = req.getServletContext().getRealPath("/");
        rootDir = FilenameUtils.separatorsToUnix(rootDir);

        String fileRelativePath = "upload/" +calendar.get(Calendar.YEAR) + "/" +(calendar.get(Calendar.MONTH)+1) + "/" +calendar.get(Calendar.DAY_OF_MONTH) + "/" + fileNameMd5+ "." +"xls";
        String sourcePath = "upload/report/节水用水季度报表样4.xls";

        File source = new File(rootDir, sourcePath);
        File target = new File(rootDir, fileRelativePath);

        target.getParentFile().mkdirs();
        FileUtils.copyFile(source, target);

        FileOutputStream fos = null;
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(9);



        //***********************************************************************************
        //这里要填数据
        //这里要填数据
        //这里要填数据
        //***********************************************************************************


        OutputStream output=resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=report.xls");
        resp.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }

    @HasPermission("Report.Query")
    @LogMsg("查询报表")
    public void listExWater(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String startStr = req.getParameter("startTime");
        String endStr = req.getParameter("endTime");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();

        ReportDTO[] reports = reportService.getExWater(startTime, endTime);

        req.setAttribute("reports", reports);
        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);

        req.getRequestDispatcher("/WEB-INF/report/reportList.jsp").forward(req,resp);
    }

    @HasPermission("Report.Query")
    @LogMsg("查询报表")
    public void listNowWater(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String startStr = req.getParameter("startTime");
        String endStr = req.getParameter("endTime");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ReportService reportService = new ReportService();

        ReportDTO[] reports = reportService.getExWater(startTime, endTime);

        req.setAttribute("reports", reports);
        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);

        req.getRequestDispatcher("/WEB-INF/report/reportList.jsp").forward(req,resp);
    }


    public void updateMonthWater(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/report/reportAdd4xls.jsp").forward(req, resp);
    }


    public void updateMonthWaterSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("xlsUrl");
        String rootDir = req.getServletContext().getRealPath("/");
        rootDir = FilenameUtils.separatorsToUnix(rootDir);

        url = url.substring(1);
        int ctxPathIndex = url.indexOf("/");
        url = url.substring(ctxPathIndex+1);

        String xlsUrl = rootDir + url;

        CompanyService companyService = new CompanyService();
        FileInputStream fis = new FileInputStream(xlsUrl);

        Workbook wb0 = new HSSFWorkbook(fis);
        Sheet sht0 = wb0.getSheetAt(0);


        Map map = new HashMap();

        for (Row r : sht0) {
            if(r.getRowNum()<1){
                continue;
            }
            String companyName = "";
            double monthWater = 0.0;

            companyName = r.getCell(0).getStringCellValue();
            monthWater = r.getCell(1).getNumericCellValue();


            map.put(companyName, monthWater);
        }
        ReportService reportService = new ReportService();
        ReportDTO[] reports = reportService.getAll();
        for(int i = 0; i < reports.length; i++){

            Double value = (Double)map.get(reports[i].getCompanyName());
            if(value == null){
                continue;
            }

            reports[i].setMonthWater(value);
            /*System.out.println("companyName:" + reports[i].getCompanyName());
            System.out.println("value:"+ map.get(reports[i].getCompanyName())+"\n");*/
            reportService.updateMonthWater(value, reports[i].getId());
        }

        fis.close();

        writeJson(resp, new AjaxResult("ok"));
    }

    public void uploadxls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String fileName = part.getSubmittedFileName();

        InputStream inStream1 = null;//获得文件的流
        InputStream inStream2 = null;

        String fileRelativePath;

        try {
            //获得文件名并检查是否符合格式
            String filename = part.getSubmittedFileName();
            String fileExt = FilenameUtils.getExtension(filename);
            if (!fileExt.equalsIgnoreCase("xls")) {
                writeJson(resp, new AjaxResult("1", "文件不符合格式", ""));
                return;
            }

            //获得网站根路径
            String rootDir = req.getServletContext().getRealPath("/");
            //转换成unix类型路径
            rootDir = FilenameUtils.separatorsToUnix(rootDir);


            Calendar calendar = Calendar.getInstance();

            //上传的文件的流
            inStream1 = part.getInputStream();
            String fileMd5 = CommonUtils.calcMD5(inStream1);

            //文件的路径
            fileRelativePath = "upload/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + fileMd5 + "." + fileExt;

            //输出看路径
            /*System.out.println("rootDir:"+rootDir);
            System.out.println("fileRealativePath" + fileRelativePath);*/

            inStream2 = new BufferedInputStream(part.getInputStream());

            File fileXls = new File(rootDir, fileRelativePath);
            fileXls.getParentFile().mkdirs();

            FileUtils.copyInputStreamToFile(inStream2, fileXls);




            writeJson(resp, new AjaxResult("0", "上传成功", req.getContextPath() + "/" + fileRelativePath));
        } catch (Exception e) {
            writeJson(resp, new AjaxResult("1", "上传失败", ""));
        } finally {
            IOUtils.closeQuietly(inStream1);
            IOUtils.closeQuietly(inStream2);
        }
    }

    public void countList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportService reportService = new ReportService();

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = (calendar.get(Calendar.MONTH)+1);

        String startStr = ReportService.getfirstDayOfNowQuarter(nowYear, nowMonth);
        String endStr = ReportService.getfirstDayOfNextQuarter(nowYear, nowMonth);

        req.setAttribute("startTime", startStr);
        req.setAttribute("endTime", endStr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int[] result = getCount(startTime, endTime);
        req.setAttribute("result", result);

        req.getRequestDispatcher("/WEB-INF/report/reportCount.jsp").forward(req, resp);
    }

    public void countSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startStr = req.getParameter("startTime");
        String endStr = req.getParameter("endTime");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startStr);
            endTime = sdf.parse(endStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int[] result = getCount(startTime, endTime);
        req.setAttribute("result", result);

        req.getRequestDispatcher("/WEB-INF/report/reportCount.jsp").forward(req, resp);
    }

    private int[] getCount(Date startTime, Date endTime){
        int[] result = new int[45];

        ReportService reportService = new ReportService();

        ReportDTO[] reports = reportService.getAll(1,startTime, endTime);
        for(int i = 0; i < reports.length; i++){
            String content = reports[i].getContent();

            String[] elements = content.split("\\|");
            for(int j = 0; j < 17; j++){
                result[j] += Double.parseDouble(elements[j]);

            }
        }


        reports = reportService.getAll(2,startTime, endTime);
        for(int i = 0; i < reports.length; i++){
            String content = reports[i].getContent();
            String[] elements = content.split("\\|");
            for(int j = 17; j < 32; j++){
                result[j] += Double.parseDouble(elements[j-17]);
            }
        }


        reports = reportService.getAll(3,startTime, endTime);
        for(int i = 0; i < reports.length; i++){
            String content = reports[i].getContent();
            String[] elements = content.split("\\|");
            for(int j = 32; j < 45; j++){
                result[j] += Double.parseDouble(elements[j-32]);
            }
        }

        return result;
    }

    public void notResidentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotResidentService notResidentService = new NotResidentService();
        NotResidentDTO[] dtos = notResidentService.getAll();
        req.setAttribute("objs", dtos);

        req.getRequestDispatcher("/WEB-INF/report/notResidentList.jsp").forward(req, resp);
    }

    public void notResidentAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/report/notResidentAdd4xls.jsp").forward(req, resp);
    }

    public void notResidentSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int water = Integer.parseInt(req.getParameter("water"));
        NotResidentService notResidentService = new NotResidentService();
        NotResidentDTO[] dtos = notResidentService.searchWater(water);
        req.setAttribute("objs", dtos);

        req.getRequestDispatcher("/WEB-INF/report/notResidentList.jsp").forward(req, resp);
    }

    public void notResidentSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("xlsUrl");
        String rootDir = req.getServletContext().getRealPath("/");
        rootDir = FilenameUtils.separatorsToUnix(rootDir);

        url = url.substring(1);
        int ctxPathIndex = url.indexOf("/");
        url = url.substring(ctxPathIndex+1);

        String xlsUrl = rootDir + url;

        NotResidentService notResidentService = new NotResidentService();
        FileInputStream fis = new FileInputStream(xlsUrl);

        Workbook wb0 = new HSSFWorkbook(fis);
        Sheet sht0 = wb0.getSheetAt(0);




        for (Row r : sht0) {
            if(r.getRowNum()<1){
                continue;
            }
            NotResidentDTO dto = new NotResidentDTO();
            NotResidentDTO tempDto = new NotResidentDTO();
            tempDto = null;

            String number="";
            number = r.getCell(0).getStringCellValue();

            dto.setNumber(number);
            dto.setName(r.getCell(1).getStringCellValue());
            dto.setStart((int)r.getCell(2).getNumericCellValue());
            dto.setEnd((int)r.getCell(3).getNumericCellValue());
            dto.setWater((int)r.getCell(4).getNumericCellValue());
            dto.setMoney(r.getCell(5).getNumericCellValue());
            dto.setType(r.getCell(6).getStringCellValue());
            dto.setCaliber(String.valueOf((int)r.getCell(7).getNumericCellValue()));
            dto.setRemark(r.getCell(8).getStringCellValue());
            dto.setLocation(r.getCell(9).getStringCellValue());




            tempDto = notResidentService.getByName(r.getCell(1).getStringCellValue());
            if(tempDto != null){
                notResidentService.updateInfo(r.getCell(1).getStringCellValue(),number,(int)r.getCell(2).getNumericCellValue(),
                        (int)r.getCell(3).getNumericCellValue(),(int)r.getCell(4).getNumericCellValue(),r.getCell(5).getNumericCellValue(),
                        String.valueOf((int)r.getCell(7).getNumericCellValue()), r.getCell(9).getStringCellValue());
            } else {
                notResidentService.addnew(dto);
            }
        }


        fis.close();

        writeJson(resp, new AjaxResult("ok"));
    }
}
