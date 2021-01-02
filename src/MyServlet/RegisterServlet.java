package MyServlet;

import DAO.ApplicantDAO;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取请求参数
        String account = request.getParameter("account");
        String user_type = request.getParameter("user_type");

        PrintWriter writer = response.getWriter();

        if(user_type == null){
            // 邮箱已经存在
            writer.write("<script>");
            writer.write("alert('请选择用户类型！');");
            writer.write("window.location.href='login_register.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();

            response.sendRedirect("login_register.html");

        }

//        判断是否是使用Ajax请求进行手机号唯一性验证

        ApplicantDAO dao = new ApplicantDAO();
        Integer count = dao.selectUserAccountCount(account);


        if (count != null && count > 0) {
            // 邮箱已经存在
            writer.write("<script>");
            writer.write("alert('邮箱已经被注册');");
            writer.write("window.location.href='login_register.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        } else {
            // 注册邮箱到数据库
            String password = request.getParameter("password");
            User user = new User(account, password, new Date(), Integer.valueOf(user_type));
            boolean flag = dao.saveApplicant(user);

            if (flag) {
                response.sendRedirect("/JobHunting/login_register.html");
                writer.write("<script>");
                writer.write("alert('注册成功！！');");
                writer.write("window.location.href='login_register.html'");
                writer.write("</script>");
                writer.flush();
                writer.close();

            } else {
                response.sendRedirect("/JobHunting/login_register.html");
                writer.write("<script>");
                writer.write("alert('注册失败！！');");
                writer.write("window.location.href='login_register.html'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
