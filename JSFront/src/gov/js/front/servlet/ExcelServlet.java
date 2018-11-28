package gov.js.front.servlet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/Excel")
public class ExcelServlet extends BaseServlet {


    public void readReport1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //excel文件路径
        String excelPath = "E:\\readExcelMaven\\test.xlsx";

        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);

                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                System.out.println(cell.toString());
                            }
                        }
                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readReport2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    public void readReport3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    public void readReport4(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }




    public void downloadReport1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell = row1.createCell(0);

        cell.setCellValue("毕节市七星关区工交企业节水用水月报表");
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,20));

        OutputStream output=resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=details.xls");
        resp.setContentType("application/msexcel");
        wb.write(output);
        output.close();

    }
    public void downloadReport2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    public void downloadReport3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    public void downloadReport4(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
