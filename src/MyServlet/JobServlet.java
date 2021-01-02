package MyServlet;

import DAO.JobDAO;
import Utils.DBUtils;
import bean.Company;
import bean.Job;
import bean.Page;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/JobServlet")
public class JobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 每行多少页
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = null;
        if (pageSizeStr != null && pageSizeStr.length() > 0)
            pageSize = Integer.valueOf(pageSizeStr);
        else
            pageSize = 4;

        //当前是第几页 currentPage
        String currentPageStr = request.getParameter("currentPage");
        Integer currentPage = null;
        if (currentPageStr != null && currentPageStr.length() > 0)
            currentPage = Integer.valueOf(currentPageStr);
        else
            currentPage = 1;

        // 一共有多少行数据totalRows
        Integer totalRows = 0;
        JobDAO dao = new JobDAO();
        String sqlCount = "select count(*) from job_info";
        totalRows = dao.getJobCount(sqlCount);
        Integer startRow = (currentPage - 1) * pageSize;
        StringBuffer sqlRow = new StringBuffer("select a.job_id, a.company_id, a.job_name, a.job_salary, a.job_info, a.job_address, a.state, b.name, b.benefit_info, b.ico from job_info a inner join company b on a.company_id = b.company_id ");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

        // 把所有的招聘信息查询出来
        List<Job> jobList = dao.getJobList(sqlRow.toString());

        Page pageBean = new Page(currentPage, pageSize, totalRows, jobList);

        request.setAttribute("pageBean", pageBean);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("content.jsp");
        requestDispatcher.forward(request, response);
    }
}
