package MyServlet;

import DAO.SearchDAO;
import bean.Job;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/SearchApplyServlet")
public class SearchApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String method = request.getParameter("method");

        if ("search".equals(method)) {
            // 获取请求参数
            String apply_search = request.getParameter("apply_search");
            String company_select_id = request.getParameter("company_select_id");

            // 根据输入内容检索
            if (company_select_id == null && apply_search != null) {
                // 检索内容
                SearchDAO dao = new SearchDAO();
                List<Job> searchJobs = dao.getSearchJobsByInput("%" + apply_search + "%");

                request.setAttribute("searchJobs", searchJobs);

                // 根据公司 id 检索
            } else if (company_select_id != null && apply_search == null) {

                SearchDAO dao = new SearchDAO();
                List<Job> searchJobsById = dao.getSearchJobsById(company_select_id);

                request.setAttribute("searchJobs", searchJobsById);

                // 根据输入的内容和公司 id 检索
            } else if (company_select_id != null && apply_search != null) {

                SearchDAO dao = new SearchDAO();
                List<Job> searchJobsByIdAndInput = dao.getSearchJobsByIdAndInput(company_select_id, "%" + apply_search + "%");

                request.setAttribute("searchJobs", searchJobsByIdAndInput);
            }


            // 转发显示结果
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("searchJobView.jsp");
            requestDispatcher.forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
