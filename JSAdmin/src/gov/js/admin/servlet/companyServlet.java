package gov.js.admin.servlet;

import gov.js.dto.CompanyDTO;
import gov.js.service.AdminUserService;
import gov.js.service.CompanyService;
import gov.js.tools.AjaxResult;
import gov.js.tools.CommonUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/Company")
@MultipartConfig
public class companyServlet extends BaseServlet {

    @HasPermission("Company.Query")
    @LogMsg("查询公司")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyService companyService = new CompanyService();
        CompanyDTO[] companies = companyService.getAll();
        req.setAttribute("companies", companies);

        req.getRequestDispatcher("/WEB-INF/company/companyList.jsp").forward(req, resp);
    }

    @HasPermission("Company.Delete")
    @LogMsg("删除公司")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        CompanyService service = new CompanyService();
        service.markDeleted(id);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Company.AddNew")
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/company/companyAdd.jsp").forward(req, resp);
    }

    @HasPermission("Company.AddNew")
    public void add4NotResident(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        req.setAttribute("name", name);

        req.getRequestDispatcher("/WEB-INF/company/companyAdd4NotResident.jsp").forward(req, resp);
    }

    @HasPermission("Company.AddNew")
    @LogMsg("新增公司")
    public void addSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNum = req.getParameter("phoneNum");
        String password = req.getParameter("password");

        String location = req.getParameter("location");
        int peopleNum = Integer.parseInt(req.getParameter("peopleNum"));
        double maxWaterMonth = Double.parseDouble(req.getParameter("maxWaterMonth"));
        String tel = req.getParameter("tel");
        String companyAdminName = req.getParameter("companyAdminName");
        String name = req.getParameter("name");
        int reportTypeId = Integer.parseInt(req.getParameter("reportTypeId"));

        CompanyDTO company = new CompanyDTO();
        company.setPhoneNum(phoneNum);
        company.setLocation(location);
        company.setPeopleNum(peopleNum);
        company.setMaxWaterMonth(maxWaterMonth);
        company.setTel(tel);
        company.setCompanyAdminName(companyAdminName);
        company.setName(name);
        company.setReportTypeId(reportTypeId);


        CompanyService companyService = new CompanyService();
        CompanyDTO companyByPhoneNum = companyService.getByPhoneNum(phoneNum);
        if(companyByPhoneNum != null){
            writeJson(resp, new AjaxResult("error","该公司已经存在",""));
            return;
        }
        int companyId = companyService.addnew(phoneNum, password);
        company.setId(companyId);
        companyService.updateInfo(company);

        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Company.Edit")
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        CompanyService companyService = new CompanyService();
        CompanyDTO company = companyService.getById(id);
        req.setAttribute("company", company);
        int reportTypeId = company.getReportTypeId();
        req.setAttribute("reportTypeId", reportTypeId);

        req.getRequestDispatcher("/WEB-INF/company/companyEdit.jsp").forward(req, resp);
    }

    @HasPermission("Company.Edit")
    @LogMsg("修改公司")
    public void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        CompanyDTO company = new CompanyDTO();
        company.setId(id);
        company.setName(req.getParameter("name"));
        company.setPhoneNum(req.getParameter("phoneNum"));
        company.setCompanyAdminName(req.getParameter("companyAdminName"));
        company.setLocation(req.getParameter("location"));
        company.setPeopleNum(Integer.parseInt(req.getParameter("peopleNum")));
        company.setTel(req.getParameter("tel"));
        company.setMaxWaterMonth(Double.parseDouble(req.getParameter("maxWaterMonth")));
        company.setReportTypeId(Integer.parseInt(req.getParameter("reportTypeId")));

        CompanyService companyService = new CompanyService();
        companyService.updateInfo(company);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Company.Edit")
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        CompanyService companyService = new CompanyService();
        CompanyDTO company = companyService.getById(id);
        req.setAttribute("company", company);

        req.getRequestDispatcher("/WEB-INF/company/companyUpdatePwd.jsp").forward(req, resp);
    }

    @HasPermission("Company.Edit")
    @LogMsg("修改公司密码")
    public void updatePwdSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String password = req.getParameter("password");
        CompanyService companyService = new CompanyService();
        companyService.updatePwd(id, password);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Company.AddNew")
    @LogMsg("新增公司")
    public void add4xls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/company/companyAdd4xls.jsp").forward(req, resp);
    }

    @HasPermission("Company.AddNew")
    @LogMsg("新增公司")
    public void add4xlsSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getParameter("xlsUrl");
        String rootDir = req.getServletContext().getRealPath("/");
        rootDir = FilenameUtils.separatorsToUnix(rootDir);

        url = url.substring(1);
        int ctxPathIndex = url.indexOf("/");
        url = url.substring(ctxPathIndex+1);

        String xlsUrl = rootDir + url;
        System.out.println("\nfilePath:"+xlsUrl+"\n");

        CompanyService companyService = new CompanyService();
        FileInputStream fis = new FileInputStream(xlsUrl);

        Workbook wb0 = new HSSFWorkbook(fis);
        Sheet sht0 = wb0.getSheetAt(0);

        for (Row r : sht0) {

            if(r.getRowNum()<1){
                continue;
            }

            CompanyDTO company=new CompanyDTO();

            company.setName(r.getCell(0).getStringCellValue());
            company.setLocation(r.getCell(1).getStringCellValue());
            company.setPeopleNum((int)r.getCell(2).getNumericCellValue());
            company.setMaxWaterMonth(r.getCell(3).getNumericCellValue());

            //company.setTel(String.valueOf(r.getCell(4).getNumericCellValue()));
            if(r.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC){   //数字
                if(String.valueOf(r.getCell(4).getNumericCellValue()).indexOf("E")==-1){
                    company.setTel(String.valueOf(r.getCell(4).getNumericCellValue()));
                }else {
                    company.setTel(new DecimalFormat("#").format(r.getCell(4).getNumericCellValue()));
                }
            }


            company.setCompanyAdminName(r.getCell(5).getStringCellValue());


            //password = r.getCell(6).getStringCellValue();
            String password = "";
            switch (r.getCell(6).getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    password = String.valueOf(r.getCell(6).getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    password = r.getCell(6).getStringCellValue();
                    break;
            }


            //company.setPhoneNum(String.valueOf(r.getCell(7).getNumericCellValue()));
            if(r.getCell(7).getCellType() == Cell.CELL_TYPE_NUMERIC){   //数字
                if(String.valueOf(r.getCell(7).getNumericCellValue()).indexOf("E")==-1){
                    company.setPhoneNum(String.valueOf(r.getCell(7).getNumericCellValue()));
                }else {
                    company.setPhoneNum(new DecimalFormat("#").format(r.getCell(7).getNumericCellValue()));
                }
            }

            company.setReportTypeId((int)r.getCell(8).getNumericCellValue());

            int companyId = companyService.addnew(company.getPhoneNum(), password);
            company.setId(companyId);
            companyService.updateInfo(company);
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
}
