package MyServlet;

import DAO.ResumeDAO;
import bean.Resume;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/uploadResumePicServlet")
@MultipartConfig
public class uploadResumePicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Part part = request.getPart("headShot");

        // 获取上传文件名
        String fileName = part.getSubmittedFileName();

        // 创建保存文件目录
        String dir = this.getServletContext().getRealPath("/Applicant/head-ico");

//        判断目录是否存在
        File imgDir = new File(dir);
        if(!imgDir.exists()){
            imgDir.mkdirs();
        }

        part.write(dir+"/"+fileName);

        ResumeDAO dao = new ResumeDAO();
        Resume session_resume = (Resume) request.getSession().getAttribute("SESSION_RESUME");
        dao.updateHeadShot(session_resume.getResume_id(), fileName);

        Resume resumeBasicInfoById = dao.getResumeBasicInfoById(session_resume.getResume_id());
        request.setAttribute("resume", resumeBasicInfoById);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/ResumeView.jsp");
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
