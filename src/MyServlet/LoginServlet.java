package MyServlet;

import DAO.ApplicantDAO;
import DAO.CompanyDAO;
import DAO.JobDAO;
import bean.Company;
import bean.Job;
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
import java.util.List;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取请求参数
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        // 登录验证
        ApplicantDAO dao = new ApplicantDAO();
        User user = dao.getApplicantByAccount(account);

        if (user != null && user.getPassword().equals(password)) {
            // 将信息存入session
            request.getSession().setAttribute("SESSION_USER", user);

            // 记住账号密码
            rememberAccountAndPassword(account, password, user.getId(), user.getUser_type(), response);

            // 求职者
            if (user.getUser_type() == 1) {

                // 判断简历是否存在
                Integer resumeID = dao.isExistResume(user.getId());
                if (resumeID != null && resumeID != 0) {
                    request.getSession().setAttribute("SESSION_RESUMEID", user.getId());
                    request.getSession().setAttribute("SESSION_USERID", user.getId());

                    // 获取所有公司的信息
                    CompanyDAO companyDAO = new CompanyDAO();
                    List<Company> companys = companyDAO.getCompanys();
                    request.getSession().setAttribute("SESSION_COMPANYS", companys);

                    response.sendRedirect("JobServlet");
//                response.sendRedirect("AddResumeServlet?method=find");
                } else {
                    // 简历不存在，跳转到简历填写页面
                    response.sendRedirect("Applicant/addResume.jsp");
                }

                // 招聘者
            } else if (user.getUser_type() == 2) {

                // 是否已经有公司信息
                JobDAO jobDAO = new JobDAO();
                Integer job_interviewCount = jobDAO.getJob_interviewCount(user.getId());
                if (job_interviewCount != null && job_interviewCount != 0) {
                    request.getSession().setAttribute("SESSION_RESUMEID", user.getId());
                    request.getSession().setAttribute("SESSION_USERID", user.getId());

                    // 将添加的工作信息传到页面
                    List<Job> interviews = jobDAO.getJob_interviewByUser_id(user.getId());
                    request.setAttribute("interviews", interviews);

                    // 查看发布的信息
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/CompanyView.jsp");
                    requestDispatcher.forward(request, response);

                } else {
                    // 还未添加公司，前往添加公司信息
                    response.sendRedirect("Applicant/addCompany.jsp");

                }

            }

        } else {
            // 用户登录信息错误
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('用户名或密码错误！！');");
            writer.write("window.location.href='/JobHunting/login_register.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }

    }

    private void rememberAccountAndPassword(String account, String password, Integer id, Integer user_type, HttpServletResponse response) {

        Cookie cookie = new Cookie("account", account);
        Cookie cookie1 = new Cookie("password", password);
        Cookie cookie2 = new Cookie("user_id", String.valueOf(id));
        Cookie cookie3 = new Cookie("user_type", String.valueOf(user_type));

        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie1.setMaxAge(60 * 60 * 24 * 365);
        cookie2.setMaxAge(60 * 60 * 24 * 365);
        cookie3.setMaxAge(60 * 60 * 24 * 365);

        response.addCookie(cookie);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
