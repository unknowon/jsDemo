package gov.js.admin.servlet;

import gov.js.dao.BannerDAO;
import gov.js.dto.BannerDTO;
import gov.js.service.BannerService;
import gov.js.tools.AjaxResult;
import gov.js.tools.CommonUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

@WebServlet("/Banner")
@MultipartConfig
public class BannerServlet extends BaseServlet {

    @HasPermission("Banner.Query")
    @LogMsg("查询轮播图")
    public void bannerList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BannerDAO bannerDAO = new BannerDAO();
        BannerDTO[] banners = bannerDAO.getAll();
        req.setAttribute("banners", banners);
        req.getRequestDispatcher("/WEB-INF/banner/bannerList.jsp").forward(req, resp);
    }

    @HasPermission("Banner.AddNew")
    public void bannerAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/banner/bannerAdd.jsp").forward(req, resp);
    }

    @HasPermission("Banner.AddNew")
    @LogMsg("新增轮播图")
    public void bannerAddSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bannerDesc = req.getParameter("bannerDesc");
        String bannerUrl = req.getParameter("bannerUrl");

        /*System.out.println("bannerUrl" + bannerUrl);
        System.out.println("bannerDesc" + bannerDesc);*/

        BannerService bannerService = new BannerService();
        BannerDTO banner = new BannerDTO();
        banner.setBannerUrl(bannerUrl);
        banner.setBannerDesc(bannerDesc);


        bannerService.addnew(banner);

        writeJson(resp, new AjaxResult("ok"));
    }

    @LogMsg("上传轮播图图片")
    public void uploadImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String fileName = part.getSubmittedFileName();

        InputStream inStream1 = null;//获得文件的流
        InputStream inStream2 = null;

        String fileRelativePath;
        String thumbFileRelativePath;

        try {
            //获得文件名并检查是否符合格式
            String filename = part.getSubmittedFileName();
            String fileExt = FilenameUtils.getExtension(filename);
            if(!fileExt.equalsIgnoreCase("jpg") && !fileExt.equalsIgnoreCase("png") && !fileExt.equalsIgnoreCase("jpeg")){
                writeJson(resp, new AjaxResult("1","文件不符合格式",""));
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
            fileRelativePath = "upload/" +calendar.get(Calendar.YEAR) + "/" +(calendar.get(Calendar.MONTH)+1) + "/" +calendar.get(Calendar.DAY_OF_MONTH) + "/" + fileMd5+ "." +fileExt;
            thumbFileRelativePath = "upload/" +calendar.get(Calendar.YEAR) + "/" +(calendar.get(Calendar.MONTH)+1) + "/" +calendar.get(Calendar.DAY_OF_MONTH) + "/" + fileMd5+ ".thumb." +fileExt;

            //输出看路径
            /*System.out.println("rootDir:"+rootDir);
            System.out.println("fileRealativePath" + fileRelativePath);*/

            inStream2 = new BufferedInputStream(part.getInputStream());
            inStream2.mark(Integer.MAX_VALUE);

            File fileThumb = new File(rootDir, thumbFileRelativePath);
            fileThumb.getParentFile().mkdirs();

            //生成缩略图
            Thumbnails.of(inStream2).size(150,150).toFile(fileThumb);
            inStream2.reset();

            //水印图
            File fileWaterMark = new File(rootDir, fileRelativePath);
            fileWaterMark.getParentFile().mkdirs();
            //生成水印
            BufferedImage imgWaterMark = ImageIO.read(new File(req.getServletContext().getRealPath("/images/watermark.png")));
            Thumbnails.of(inStream2).scale(1f).watermark(Positions.BOTTOM_RIGHT,imgWaterMark, 0.5f).toFile(fileWaterMark);

            //设置路径？
            /*ArticleDTO article = new ArticleDTO();
            article.setArticleImg("http://localhost:8080/JSAdmin/" + fileRelativePath);*/


            /*housePic.setUrl("http://localhost:8080/ZuAdmin/" + fileRelativePath);
            housePic.setThumbUrl("http://localhost:8080/ZuAdmin/" + thumbFileRelativePath);*/

            //System.out.println(req.getContextPath()+"/"+fileRelativePath);

            writeJson(resp, new AjaxResult("0","上传成功", req.getContextPath()+"/"+fileRelativePath));
        } catch (Exception e){
            writeJson(resp, new AjaxResult("1","上传失败",""));
        } finally{
            IOUtils.closeQuietly(inStream1);
            IOUtils.closeQuietly(inStream2);
        }

    }

    @HasPermission("Banner.Delete")
    @LogMsg("删除轮播图")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        BannerService bannerService = new BannerService();
        bannerService.markDeleted(id);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Banner.Edit")
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        BannerService bannerService = new BannerService();
        BannerDTO banner = bannerService.getById(id);
        req.setAttribute("banner", banner);

        req.getRequestDispatcher("/WEB-INF/banner/bannerEdit.jsp").forward(req, resp);
    }

    @HasPermission("Banner.Edit")
    @LogMsg("修改轮播图")
    public void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String bannerDesc = req.getParameter("bannerDesc");
        String bannerUrl = req.getParameter("bannerUrl");

        BannerService bannerService = new BannerService();
        BannerDTO banner = new BannerDTO();
        banner.setId(id);
        banner.setBannerDesc(bannerDesc);
        banner.setBannerUrl(bannerUrl);
        bannerService.update(banner);

        writeJson(resp, new AjaxResult("ok"));
    }
}
