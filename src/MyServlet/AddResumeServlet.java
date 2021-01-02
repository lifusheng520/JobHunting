package MyServlet;

import DAO.ResumeDAO;
import bean.Resume;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/AddResumeServlet")
public class AddResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        // 设置请求和响应编码
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");

        String method = request.getParameter("method");

        if ("add".equals(method)) {
            // 获取简历
            Resume resume = getRequestResume(request);

            ResumeDAO dao = new ResumeDAO();

            Integer resumeId = dao.saveResumeBasicInfo(resume);

            request.setAttribute("resume", resume);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/ResumeView.jsp");
            requestDispatcher.forward(request, response);

        } else if ("find".equals(method)) {
            //查询简历信息
            String resumeId = null;

            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("user_id")) {
                    resumeId = cookie.getValue();
                    break;
                }

            if (resumeId == null) {
                response.sendRedirect("login_register.html");
            }

            ResumeDAO dao = new ResumeDAO();
            Integer id = Integer.valueOf(resumeId);
            Resume resume = dao.getResumeBasicInfoById(id);

            request.setAttribute("resume", resume);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/ResumeView.jsp");
            requestDispatcher.forward(request, response);

        } else if ("update".equals(method)) {
            // 修改简历
            String resume_id = request.getParameter("resumeId");

            Resume resume = getRequestResume(request);
            resume.setResume_id(Integer.valueOf(resume_id));

            ResumeDAO dao = new ResumeDAO();
            boolean flag = dao.updateResumeBasicInfo(resume);
            request.setAttribute("resume", resume);

            if (flag){
                request.setAttribute("updateState", 1);
            } else {
                request.setAttribute("updateState", 0);
            }

            request.getRequestDispatcher("Applicant/ResumeView.jsp").forward(request, response);
        }

    }

    private Resume getRequestResume(HttpServletRequest request) {
        //获取请求参数
        String realname = request.getParameter("realname");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String location = request.getParameter("location");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String experience = request.getParameter("experience");

        User session_user = (User) request.getSession().getAttribute("SESSION_USER");

        Resume resume = new Resume(session_user.getId(), realname, gender, birthday, location, telephone, email, experience, "default.jpg");

        return resume;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
