package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        //设置响应编码
        response.setContentType("text/html;charset=UTF-8");


        // 解决重复登录问题：session里面有登录数据就不需要登录了
        // 简单判断缓存中是否有登录用户
        if(session.getAttribute("SESSION_USER") == null){   // 没有用户（未登录）
            // 驳回访问请求，并重定向到登录界面
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('请先登录在进行操作！');");
            writer.write("window.location.href = 'login_register.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
            // 没登录，驳回访问请求并重定向到登录页面
            //response.sendRedirect("/offers/login.html");

            return ;
        } else {
            // 放行，交给下一个过滤器
            chain.doFilter(req, resp);

            return ;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
