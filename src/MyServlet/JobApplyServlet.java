package MyServlet;

import DAO.ApplyDAO;
import bean.Job;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/JobApplyServlet")
public class JobApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 获取请求参数
        String job_id = request.getParameter("job_id");
        String user_id = request.getParameter("user_id");
        String method = request.getParameter("method");

        if ("add".equals(method)) {
            // 添加求职申请信息
            ApplyDAO dao = new ApplyDAO();
            dao.addApplyInfo(user_id, job_id);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("content.jsp");
            requestDispatcher.forward(request, response);

        } else if ("find".equals(method)) {

            // 查找用户的求职申请
            ApplyDAO dao = new ApplyDAO();

            List<Job> jobInfo = dao.getJobInfo(Integer.valueOf(user_id));

            request.setAttribute("jobList", jobInfo);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/ApplyView.jsp");
            requestDispatcher.forward(request, response);

        } else if ("delete".equals(method)) {
            // 删除用户提交的求职申请
            ApplyDAO dao = new ApplyDAO();

            boolean flag = dao.deleteJobInfo(user_id, job_id);

            List<Job> jobList = (List<Job>) request.getAttribute("jobList");
            request.setAttribute("jobList", jobList);

            if (flag)
                request.setAttribute("apply_delete_state", 1);
            else
                request.setAttribute("apply_delete_state", 0);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/ApplyView.jsp");
            requestDispatcher.forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
