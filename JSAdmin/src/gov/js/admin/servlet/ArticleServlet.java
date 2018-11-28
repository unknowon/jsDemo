package gov.js.admin.servlet;

import gov.js.dto.ArticleDTO;
import gov.js.dto.LinkDTO;
import gov.js.service.ArticleService;
import gov.js.service.LinkService;
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
import java.io.*;
import java.util.Calendar;

@WebServlet("/Article")
@MultipartConfig
public class ArticleServlet extends BaseServlet {

    @HasPermission("Article.Query")
    @LogMsg("查询文章")
    public void articleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int typeId = Integer.parseInt(req.getParameter("typeId"));

        ArticleService articleService = new ArticleService();
        ArticleDTO[] articles = articleService.getAllByTypeId(typeId);

        req.setAttribute("typeId", typeId);
        req.setAttribute("articles", articles);


        req.getRequestDispatcher("/WEB-INF/article/articleList.jsp").forward(req, resp);
    }

    @HasPermission("Article.AddNew")
    public void articleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int typeId = Integer.parseInt(req.getParameter("typeId"));
        req.setAttribute("typeId", typeId);

        req.getRequestDispatcher("/WEB-INF/article/articleAdd.jsp").forward(req, resp);
    }

    @HasPermission("Article.AddNew")
    @LogMsg("新增文章")
    public void articleAddSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String articleTitle = req.getParameter("articleTitle");
        String articleKeyWord = req.getParameter("articleKeyWord");
        String articleDesc = req.getParameter("articleDesc");
        String articleAuthor = req.getParameter("articleAuthor");
        String articleImg = req.getParameter("articleImg");
        String articleContent = req.getParameter("articleContent");
        int typeId = Integer.parseInt(req.getParameter("typeId"));

        ArticleService articleService = new ArticleService();
        ArticleDTO article = new ArticleDTO();
        article.setArticleTitle(articleTitle);
        article.setArticleKeyWords(articleKeyWord);
        article.setArticleDesc(articleDesc);
        article.setArticleAuthor(articleAuthor);
        article.setArticleImg(articleImg);
        article.setArticleContent(articleContent);
        article.setArticleTypeId(typeId);

        articleService.addnew(article);


        writeJson(resp, new AjaxResult("ok"));
    }

    @LogMsg("上传文章图片")
    public void uploadImage1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            Thumbnails.of(inStream2).size(203, 130).watermark(Positions.BOTTOM_RIGHT,imgWaterMark, 0.5f).toFile(fileWaterMark);

            //设置路径？
            /*ArticleDTO article = new ArticleDTO();
            article.setArticleImg("http://localhost:8080/JSAdmin/" + fileRelativePath);*/


            /*housePic.setUrl("http://localhost:8080/ZuAdmin/" + fileRelativePath);
            housePic.setThumbUrl("http://localhost:8080/ZuAdmin/" + thumbFileRelativePath);*/


            writeJson(resp, new AjaxResult("0","上传成功", req.getContextPath()+"/"+fileRelativePath));
        } catch (Exception e){
            writeJson(resp, new AjaxResult("1","上传失败",""));
        } finally{
            IOUtils.closeQuietly(inStream1);
            IOUtils.closeQuietly(inStream2);
        }



    }


    @HasPermission("Article.Delete")
    @LogMsg("删除文章")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ArticleService articleService = new ArticleService();
        articleService.markDeleted(id);
        writeJson(resp, new AjaxResult("ok"));
    }

    @HasPermission("Article.Edit")
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        ArticleService articleService = new ArticleService();
        ArticleDTO article = articleService.getById(id);
        req.setAttribute("article", article);
        req.getRequestDispatcher("/WEB-INF/article/articleEdit.jsp").forward(req, resp);
    }

    @HasPermission("Article.Edit")
    @LogMsg("修改文章")
    public void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String articleTitle = req.getParameter("articleTitle");
        String articleDesc = req.getParameter("articleDesc");
        String articleKeyWords = req.getParameter("articleKeyWord");
        String articleAuthor = req.getParameter("articleAuthor");
        String articleImg = req.getParameter("articleImg");
        String articleContent = req.getParameter("articleContent");

        ArticleDTO article = new ArticleDTO();
        article.setArticleId(id);
        article.setArticleTitle(articleTitle);
        article.setArticleDesc(articleDesc);
        article.setArticleKeyWords(articleKeyWords);
        article.setArticleAuthor(articleAuthor);
        article.setArticleImg(articleImg);
        article.setArticleContent(articleContent);

        ArticleService articleService = new ArticleService();
        articleService.update(article);
        writeJson(resp, new AjaxResult("ok"));
    }

    public void articleView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ArticleService articleService = new ArticleService();
        ArticleDTO article = articleService.getById(id);
        req.setAttribute("article", article);

        //获取友链
        LinkService linkService = new LinkService();
        LinkDTO[] links = linkService.getAll();
        req.setAttribute("links", links);

        req.getRequestDispatcher("/WEB-INF/article/articleView.jsp").forward(req, resp);
    }
}
