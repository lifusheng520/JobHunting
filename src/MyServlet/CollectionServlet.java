package MyServlet;

import DAO.CollectionDAO;
import bean.Job;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/CollectionServlet")
public class CollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String method = request.getParameter("method");

        if ("add".equals(method)) {

            User session_user = (User) request.getSession().getAttribute("SESSION_USER");

            Integer account = session_user.getId();
            CollectionDAO dao = new CollectionDAO();
            // 添加收藏
            String[] collections = request.getParameterValues("collection");

            if (collections != null) {
                for (String id : collections) {
                    dao.addCollection(account, id);
                }
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('收藏成功！！');");
                writer.write("window.location.href='/JobHunting/content.jsp'");
                writer.write("</script>");
                writer.flush();
                writer.close();

            } else {
                // 未添加收藏

                request.getRequestDispatcher("content.jsp").forward(request,response);
            }

        } else if ("view".equals(method)) {

            CollectionDAO dao = new CollectionDAO();

            // 获取账号信息
            User session_user = (User) request.getSession().getAttribute("SESSION_USER");

            // 获取收藏的工作信息
            List<Job> collectionList = dao.getCollectionJobIdSet(session_user.getId());

            request.setAttribute("collections", collectionList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Applicant/CollectionView.jsp");
            requestDispatcher.forward(request, response);

        } else if ("delete".equals(method)) {

            CollectionDAO dao = new CollectionDAO();

            //获取删除信息
            String[] delete_collections = request.getParameterValues("delete_collection");

            // 根据 选择的职业的id  将选择的收藏全部删除
            dao.deleteCollections(delete_collections);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("CollectionServlet?method=view");
            requestDispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
