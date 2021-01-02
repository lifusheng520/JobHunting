package MyServlet;

import DAO.ApplyDAO;
import bean.ApplyInfo;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/RecruitApplyServlet")
public class RecruitApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String method = request.getParameter("method");

        if("find".equals(method)){
            User session_user = (User) request.getSession().getAttribute("SESSION_USER");


            // 查看向招聘者提交的求职申请
            ApplyDAO dao = new ApplyDAO();
            List<ApplyInfo> applyInfos = dao.getApplyOfAppliants(session_user.getId());

            request.setAttribute("applyInfos", applyInfos);

            request.getRequestDispatcher("Applicant/RecruitApplyView.jsp").forward(request, response);

        } else if ("updateReply".equals(method)) {
            // 回复求职者
            ApplyDAO dao = new ApplyDAO();

            // 获取请求参数
            String content = request.getParameter("reply_content");
            String id = request.getParameter("id");
            String job_id = request.getParameter("job_id");

            // 更新回复信息
            dao.updateReply(Integer.valueOf(id), Integer.valueOf(job_id), content);

            User session_user = (User) request.getSession().getAttribute("SESSION_USER");


            // 查看向招聘者提交的求职申请
            List<ApplyInfo> applyInfos = dao.getApplyOfAppliants(session_user.getId());

            request.setAttribute("applyInfos", applyInfos);

            request.getRequestDispatcher("Applicant/RecruitApplyView.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
