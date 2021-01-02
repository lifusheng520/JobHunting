package MyServlet;

import DAO.PersonalDAO;
import bean.Resume;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/PersonalServlet")
public class PersonalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String method = request.getParameter("method");

        if ("read".equals(method)) {
            Integer session_userid = (Integer) request.getSession().getAttribute("SESSION_USERID");

            //读取个人信息
            PersonalDAO dao = new PersonalDAO();
            User userInfo = dao.getUserInfo(session_userid);

            //获取头像
            Resume resume = dao.getHeadShot(session_userid);

            // 设置响应参数
            request.setAttribute("USERINFO", userInfo);
            request.setAttribute("RESUME", resume);

            // 请求转发到  Personal.jsp
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/Personal.jsp");
            requestDispatcher.forward(request, response);

        } else if("write".equals(method)){
            // 修改个人密码
            PersonalDAO dao = new PersonalDAO();

            Integer session_userid = (Integer) request.getSession().getAttribute("SESSION_USERID");
            User userInfo = dao.getUserInfo(session_userid);
            Resume resume = dao.getHeadShot(session_userid);

            // 设置响应参数
            request.setAttribute("USERINFO", userInfo);
            request.setAttribute("RESUME", resume);

            // 获取请求参数
            String account = request.getParameter("account");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");

            // 判断两次密码
            if(!password1.isEmpty() && password1.equals(password2)){

                boolean flag = dao.updatePassword(account, password1);

                if(flag){
                    // 修改成功
                    request.setAttribute("updateState", 1);

                } else {
                    request.setAttribute("updateState", 0);

                }

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/Personal.jsp");
                requestDispatcher.forward(request,response);

            } else {
                // 两次密码不等，修改失败
                request.setAttribute("updateState", 2);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/Personal.jsp");
                requestDispatcher.forward(request,response);
            }
        }

        request.getRequestDispatcher("content.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
