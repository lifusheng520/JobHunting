package MyServlet;

import DAO.CompanyDAO;
import DAO.JobDAO;
import bean.Company;
import bean.Job;
import bean.JobBasicInfo;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/AddCompanyServlet")
public class AddCompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String method = request.getParameter("method");

        if ("add".equals(method)) {
            // 获取请求参数
            String name = request.getParameter("name");
            String benefit_info = request.getParameter("benefit_info");
            String job_name = request.getParameter("job_name");
            String job_salary = request.getParameter("job_salary");
            String job_info = request.getParameter("job_info");
            String job_address = request.getParameter("job_address");
            String state = request.getParameter("state");

            Company company = new Company(name, benefit_info, "default.jpg");

            // 添加公司信息
            CompanyDAO dao = new CompanyDAO();
            Integer company_key = dao.addCompany(company);

            JobBasicInfo jobBasicInfo = new JobBasicInfo(company_key, job_name, job_salary, job_info, job_address, Integer.valueOf(state));

            // 添加岗位信息
            JobDAO jobDAO = new JobDAO();
            Integer job_key = jobDAO.addJobBasicInfo(jobBasicInfo);

            company.setCompany_id(company_key);
            jobBasicInfo.setJob_id(job_key);

            // 保存招聘者添加的信息
            User session_user = (User) request.getSession().getAttribute("SESSION_USER");
            jobDAO.addJob_interview(session_user.getId(), job_key);

            // 将添加的工作信息传到页面
            List<Job> interviews = jobDAO.getJob_interviewByUser_id(job_key);
            request.setAttribute("interviews", interviews);

            // 前往 CompanyView.jsp 显示添加的招聘信息
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/CompanyView.jsp");
            requestDispatcher.forward(request, response);

        } else if ("update".equals(method)) {
            // 更新招聘信息
            JobDAO jobDAO = new JobDAO();
            CompanyDAO companyDAO = new CompanyDAO();

            // 获取请求参数
            String job_id = request.getParameter("job_id");
            String company_id = request.getParameter("company_id");
            String name = request.getParameter("name");
            String benefit_info = request.getParameter("benefit_info");
            String ico = request.getParameter("ico");
            String job_name = request.getParameter("job_name");
            String job_salary = request.getParameter("job_salary");
            String job_info = request.getParameter("job_info");
            String job_address = request.getParameter("job_address");
            String state = request.getParameter("state");

            Company company = new Company(name, benefit_info, "default.jpg");
            JobBasicInfo jobBasicInfo = new JobBasicInfo(Integer.valueOf(company_id), job_name, job_salary, job_info, job_address, Integer.valueOf(state));
            jobBasicInfo.setJob_id(Integer.valueOf(job_id));
            company.setCompany_id(Integer.valueOf(company_id));

            boolean b = jobDAO.updateJobBasicInfo(jobBasicInfo);
            boolean b1 = companyDAO.updateCompany(company);

            if (b && b1) {
                request.setAttribute("interview_update_state", 1);
            } else {
                request.setAttribute("interview_update_state", 0);
            }

            // 将添加的工作信息传到页面
            User session_user = (User) request.getSession().getAttribute("SESSION_USER");
            List<Job> interviews = jobDAO.getJob_interviewByUser_id(session_user.getId());
            request.setAttribute("interviews", interviews);

            request.getRequestDispatcher("Applicant/CompanyView.jsp").forward(request, response);

        } else if ("delete".equals(method)) {
            // 删除招聘信息
            JobDAO jobDAO = new JobDAO();
            CompanyDAO companyDAO = new CompanyDAO();

            // 获取删除的请求参数
            String job_id = request.getParameter("job_id");
            String company_id = request.getParameter("company_id");
            User session_user = (User) request.getSession().getAttribute("SESSION_USER");

            boolean b = jobDAO.deleteJob_info(Integer.valueOf(job_id));
            boolean b1 = companyDAO.deleteCompany(Integer.valueOf(company_id));
            jobDAO.deleteJob_interview(session_user.getId(), Integer.valueOf(job_id));

            if (b && b1) {
                request.setAttribute("interview_delete_state", 1);
            } else {
                request.setAttribute("interview_delete_state", 0);
            }

            // 将添加的工作信息传到页面

            List<Job> interviews = jobDAO.getJob_interviewByUser_id(session_user.getId());
            request.setAttribute("interviews", interviews);

            request.getRequestDispatcher("Applicant/CompanyView.jsp").forward(request, response);

        } else if ("find".equals(method)) {
            // 查找招聘信息
            JobDAO jobDAO = new JobDAO();
            User session_user = (User) request.getSession().getAttribute("SESSION_USER");
            List<Job> interviews = jobDAO.getJob_interviewByUser_id(session_user.getId());

            // 将添加的工作信息传到页面
            request.setAttribute("interviews", interviews);

            request.getRequestDispatcher("Applicant/CompanyView.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
