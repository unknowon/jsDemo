package gov.js.front.servlet;

import gov.js.dto.ArticleDTO;
import gov.js.dto.CompanyDTO;
import gov.js.dto.LinkDTO;
import gov.js.dto.ReportDTO;
import gov.js.front.utils.FrontUtils;
import gov.js.service.ArticleService;
import gov.js.service.CompanyService;
import gov.js.service.LinkService;
import gov.js.service.ReportService;
import gov.js.tools.AjaxResult;
import gov.js.tools.CommonUtils;
import gov.js.tools.VerifyCodeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;

@WebServlet("/Report")
public class ReportServlet extends BaseServlet {
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int companyId = FrontUtils.getCurrentUserId(req);
        if(companyId == -1){
            req.getRequestDispatcher("/Index?action=login").forward(req, resp);
            return;
        }

        /*System.out.println("companyId"+companyId);*/
        CompanyDTO company = FrontUtils.getCurrentCompany(req);

        int id = company.getReportTypeId();

        //获取友链
        LinkService linkService = new LinkService();
        LinkDTO[] links = linkService.getAll();
        req.setAttribute("links", links);

        req.getRequestDispatcher("/WEB-INF/report/reportAdd"+ id +".jsp").forward(req, resp);
    }


    public void addSubmit1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();
        String fileNameMd5 = CommonUtils.calcMD5(VerifyCodeUtils.generateVerifyCode(6));
        String rootDir = req.getServletContext().getRealPath("/");
        rootDir = FilenameUtils.separatorsToUnix(rootDir);
        /*System.out.println(rootDir);*/

        String fileRelativePath = "upload/" +calendar.get(Calendar.YEAR) + "/" +(calendar.get(Calendar.MONTH)+1) + "/" +calendar.get(Calendar.DAY_OF_MONTH) + "/" + fileNameMd5+ "." +"xls";
        String sourcePath = "upload/report/工交企业节水用水月报表样1.xls";

        File source = new File(rootDir, sourcePath);
        File target = new File(rootDir, fileRelativePath);

        target.getParentFile().mkdirs();
        FileUtils.copyFile(source, target);


        int companyId = FrontUtils.getCurrentUserId(req);
        CompanyDTO company = FrontUtils.getCurrentCompany(req);
        int id = company.getReportTypeId();

        StringBuilder sb = new StringBuilder();
        String[] reportPras = new String[26];

        for(int i = 0; i < 25; i++){
            reportPras[i] = req.getParameter("input"+(i+1));
            sb.append(reportPras[i] + "|");
        }
        reportPras[25] = req.getParameter("input"+(25+1));
        sb.append(reportPras[25]);


        FileOutputStream fos = null;
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(9);



        for(int i = 0; i < 4; i++){
            HSSFCell cell = row.getCell(i+1);
            cell.setCellValue(reportPras[i]);
        }
        for(int i = 4; i < 9; i++){
            HSSFCell cell = row.getCell(i+2);
            cell.setCellValue(reportPras[i]);
        }
        HSSFCell cell12 = row.getCell(12);
        cell12.setCellValue(reportPras[9]);
        for(int i = 10; i < 15; i++){
            HSSFCell cell = row.getCell(i+4);
            cell.setCellValue(reportPras[i]);
        }
        for(int i = 15; i < 18; i++){
            HSSFCell cell = row.getCell(i+6);
            cell.setCellValue(reportPras[i]);
        }


        /*HSSFCell cell1 = row.getCell(1);
        cell1.setCellValue(reportPras[0]);

        HSSFCell cell2 = row.getCell(2);
        cell2.setCellValue(reportPras[1]);

        HSSFCell cell3 = row.getCell(3);
        cell3.setCellValue(reportPras[2]);

        HSSFCell cell4 = row.getCell(4);
        cell4.setCellValue(reportPras[3]);*/

        /*HSSFCell cell6 = row.getCell(6);
        cell6.setCellValue(reportPras[4]);

        HSSFCell cell7 = row.getCell(7);
        cell7.setCellValue(reportPras[5]);

        HSSFCell cell8 = row.getCell(8);
        cell8.setCellValue(reportPras[6]);

        HSSFCell cell9 = row.getCell(9);
        cell9.setCellValue(reportPras[7]);

        HSSFCell cell10 = row.getCell(10);
        cell10.setCellValue(reportPras[8]);*/

        /*HSSFCell cell13 = row.getCell(14);
        cell13.setCellValue(reportPras[10]);

        HSSFCell cell14 = row.getCell(15);
        cell14.setCellValue(reportPras[11]);

        HSSFCell cell15 = row.getCell(16);
        cell15.setCellValue(reportPras[12]);

        HSSFCell cell16 = row.getCell(17);
        cell16.setCellValue(reportPras[13]);

        HSSFCell cell17 = row.getCell(18);
        cell17.setCellValue(reportPras[14]);*/

        /*HSSFCell cell19 = row.getCell(21);
        cell19.setCellValue(reportPras[15]);

        HSSFCell cell20 = row.getCell(22);
        cell20.setCellValue(reportPras[16]);

        HSSFCell cell21 = row.getCell(23);
        cell21.setCellValue(reportPras[17]);*/


        HSSFRow row5 = sheet.getRow(4);
        HSSFCell cell51 = row5.getCell(0);
        cell51.setCellValue("填报单位(章) "+ reportPras[18] +" 行业号："+reportPras[19]+"  系统号："+reportPras[20]+"  "+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月");

        HSSFRow row11 = sheet.getRow(10);
        HSSFCell cell101 = row11.getCell(0);
        cell101.setCellValue("单位节水管理部门："+reportPras[22]+" 电话："+reportPras[23]+" 填表人："+reportPras[24]+" 填报日期："+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日 水表号："+reportPras[25]);

        fos = new FileOutputStream(target);
        wb.write(fos);
        fos.close();
        wb.close();

        ReportService reportService = new ReportService();
        ReportDTO report = new ReportDTO();
        report.setReportTypeId(id);
        report.setContent(sb.toString());
        report.setCompanyId(companyId);

        report.setWaterType(Integer.parseInt(reportPras[6]));
        report.setNewWater(Double.parseDouble(reportPras[8]));
        report.setUnconvWater(Double.parseDouble(reportPras[9]));
        report.setReWater((Double.parseDouble(reportPras[12])+Integer.parseInt(reportPras[13])+Integer.parseInt(reportPras[14])));
        report.setReWaterRate(Double.parseDouble(reportPras[16]));
        report.setAttachments(req.getParameter("attachments"));

        report.setFileUrl(req.getContextPath()+"/"+fileRelativePath);

        reportService.addnew(report);

        writeJson(resp, new AjaxResult("ok"));
    }


    public void addSubmit2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();
        String fileNameMd5 = CommonUtils.calcMD5(VerifyCodeUtils.generateVerifyCode(6));
        String rootDir = req.getServletContext().getRealPath("/");
        rootDir = FilenameUtils.separatorsToUnix(rootDir);
        /*System.out.println(rootDir);*/

        String fileRelativePath = "upload/" +calendar.get(Calendar.YEAR) + "/" +(calendar.get(Calendar.MONTH)+1) + "/" +calendar.get(Calendar.DAY_OF_MONTH) + "/" + fileNameMd5+ "." +"xls";
        String sourcePath = "upload/report/毕节市服务业医院节水用水月报表样2表.xls";

        File source = new File(rootDir, sourcePath);
        File target = new File(rootDir, fileRelativePath);

        target.getParentFile().mkdirs();
        FileUtils.copyFile(source, target);


        int companyId = FrontUtils.getCurrentUserId(req);
        CompanyDTO company = FrontUtils.getCurrentCompany(req);
        int id = company.getReportTypeId();

        StringBuilder sb = new StringBuilder();
        String[] reportPras = new String[24];

        for(int i = 0; i < 23; i++){
            reportPras[i] = req.getParameter("input"+(i+1));
            sb.append(reportPras[i] + "|");
        }
        reportPras[23] = req.getParameter("input"+(23+1));
        sb.append(reportPras[23]);


        FileOutputStream fos = null;
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(9);



        for(int i = 0; i < 16; i++){
            HSSFCell cell = row.getCell(i+1);
            cell.setCellValue(reportPras[i]);
        }


        HSSFRow row5 = sheet.getRow(4);
        HSSFCell cell51 = row5.getCell(0);
        cell51.setCellValue("填报单位(章) "+ reportPras[16] +" 行业号："+reportPras[17]+"  系统号："+reportPras[18]+"  "+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月");

        HSSFRow row11 = sheet.getRow(10);
        HSSFCell cell101 = row11.getCell(0);
        cell101.setCellValue("单位节水管理部门："+reportPras[20]+" 电话："+reportPras[21]+" 填表人："+reportPras[22]+" 填报日期："+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日 水表号："+reportPras[23]);

        fos = new FileOutputStream(target);
        wb.write(fos);
        fos.close();
        wb.close();

        ReportService reportService = new ReportService();
        ReportDTO report = new ReportDTO();
        report.setReportTypeId(id);
        report.setContent(sb.toString());
        report.setCompanyId(companyId);

        report.setWaterType(Integer.parseInt(reportPras[8]));
        report.setNewWater(Double.parseDouble(reportPras[9]));
        report.setUnconvWater(Double.parseDouble(reportPras[10]));
        report.setReWater(Double.parseDouble(reportPras[12]));
        report.setReWaterRate(Double.parseDouble(reportPras[14]));
        report.setAttachments(req.getParameter("attachments"));

        report.setFileUrl(req.getContextPath()+"/"+fileRelativePath);

        reportService.addnew(report);

        writeJson(resp, new AjaxResult("ok"));
    }


    public void addSubmit3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();
        String fileNameMd5 = CommonUtils.calcMD5(VerifyCodeUtils.generateVerifyCode(6));
        String rootDir = req.getServletContext().getRealPath("/");
        rootDir = FilenameUtils.separatorsToUnix(rootDir);
        /*System.out.println(rootDir);*/

        String fileRelativePath = "upload/" +calendar.get(Calendar.YEAR) + "/" +(calendar.get(Calendar.MONTH)+1) + "/" +calendar.get(Calendar.DAY_OF_MONTH) + "/" + fileNameMd5+ "." +"xls";
        String sourcePath = "upload/report/毕节市机关学校用水月报表样3.xls";

        File source = new File(rootDir, sourcePath);
        File target = new File(rootDir, fileRelativePath);

        target.getParentFile().mkdirs();
        FileUtils.copyFile(source, target);


        int companyId = FrontUtils.getCurrentUserId(req);
        CompanyDTO company = FrontUtils.getCurrentCompany(req);
        int id = company.getReportTypeId();

        StringBuilder sb = new StringBuilder();
        String[] reportPras = new String[22];

        for(int i = 0; i < 21; i++){
            reportPras[i] = req.getParameter("input"+(i+1));
            sb.append(reportPras[i] + "|");
        }
        reportPras[21] = req.getParameter("input"+(21+1));
        sb.append(reportPras[21]);


        FileOutputStream fos = null;
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(9);



        for(int i = 0; i < 14; i++){
            HSSFCell cell = row.getCell(i+1);
            cell.setCellValue(reportPras[i]);
        }


        HSSFRow row5 = sheet.getRow(4);
        HSSFCell cell51 = row5.getCell(0);
        cell51.setCellValue("填报单位(章) "+ reportPras[14] +" 行业号："+reportPras[15]+"  系统号："+reportPras[16]+"  "+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月");

        HSSFRow row11 = sheet.getRow(10);
        HSSFCell cell101 = row11.getCell(0);
        cell101.setCellValue("单位节水管理部门："+reportPras[18]+" 电话："+reportPras[19]+" 填表人："+reportPras[20]+" 填报日期："+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日 水表号："+reportPras[21]);

        fos = new FileOutputStream(target);
        wb.write(fos);
        fos.close();
        wb.close();

        ReportService reportService = new ReportService();
        ReportDTO report = new ReportDTO();
        report.setReportTypeId(id);
        report.setContent(sb.toString());
        report.setCompanyId(companyId);

        report.setWaterType(Integer.parseInt(reportPras[3]));
        report.setNewWater(Double.parseDouble(reportPras[5]));
        report.setUnconvWater(Double.parseDouble(reportPras[7]) + Double.parseDouble(reportPras[8]));
        report.setReWater(Double.parseDouble(reportPras[10]));
        report.setReWaterRate(Double.parseDouble(reportPras[12]));

        report.setAttachments(req.getParameter("attachments"));
        report.setFileUrl(req.getContextPath()+"/"+fileRelativePath);

        reportService.addnew(report);

        writeJson(resp, new AjaxResult("ok"));
    }


    public void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = FrontUtils.getCurrentUserId(req);
        if(companyId == -1){
            req.getRequestDispatcher("/Index?action=login").forward(req, resp);
            return;
        }

        ReportService reportService = new ReportService();
        ReportDTO[] reports = reportService.getAll(companyId);
        req.setAttribute("reports", reports);

        //获取友链
        LinkService linkService = new LinkService();
        LinkDTO[] links = linkService.getAll();
        req.setAttribute("links", links);

        req.getRequestDispatcher("/WEB-INF/report/reportFind.jsp").forward(req, resp);
    }

}
