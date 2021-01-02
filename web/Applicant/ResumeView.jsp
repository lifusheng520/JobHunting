<%@ page import="bean.Resume" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2020-12-30
  Time: 上午 09:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ResumeView</title>
    <link href="css/CssOfTop.css" rel="stylesheet" type="text/css"/>
</head>
<body background="img/img_index/bk_content_top.png" bgcolor="black">

<div class="top">
    <div class="personal">
        <div><a href="${pageContext.request.contextPath}/ExitServlet">退出</a></div>
        <div><a href="${pageContext.request.contextPath}/JobServlet">主页</a></div>
    </div>
</div>

<c:if test="${requestScope.updateState != null}">
    <c:choose>
        <c:when test="${requestScope.updateState == 1}">
            <script>
                alert("修改成功！！");
            </script>
        </c:when>
        <c:when test="${requestScope.updateState == 0}">
            <script>
                alert("修改失败！！");
            </script>
        </c:when>
    </c:choose>
</c:if>


<form action=" ${pageContext.request.contextPath}/AddResumeServlet?method=update&resumeId=${pageContext.request.getAttribute("resume").resume_id}"
      method="post">
    <table align="center" style="margin-top: 120px;" bgcolor="#f5f5f5" width="50%" height="550px">
        <tr align="center">
            <td colspan="2"><h1>我的简历信息</h1></td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <p><img src="Applicant/head-ico/${pageContext.request.getAttribute("resume").head_shot}"></p>
            </td>
        </tr>
        <tr>
            <td>&nbsp</td>
            <td>&nbsp</td>
        </tr>
        <tr>
            <td align="right" height="40px"><p>真实姓名：</p></td>
            <td>
                <p><input type="text" name="realname" value="${pageContext.request.getAttribute("resume").realname}">
                </p>
            </td>
        </tr>
        <tr>
            <td align="right" height="40px"><p>性别：</p></td>
            <td>
                <p><input type="text" name="gender" value="${pageContext.request.getAttribute("resume").gender}"></p>
            </td>
        </tr>
        <tr>
            <td align="right" height="40px">出生日期：</td>
            <td>
                <p><input type="text" name="birthday" value="${pageContext.request.getAttribute("resume").birthday}">
                </p>
            </td>
        </tr>
        <tr>
            <td align="right" height="40px">住址：</td>
            <td>
                <p><input type="text" name="location" value="${pageContext.request.getAttribute("resume").location}">
                </p>
            </td>
        </tr>
        <tr>
            <td align="right" height="40px">电话：</td>
            <td>
                <p><input type="text" name="telephone" value="${pageContext.request.getAttribute("resume").telephone}">
                </p>
            </td>
        </tr>
        <tr>
            <td align="right" height="40px">邮箱：</td>
            <td>
                <p><input type="text" name="email" value="${pageContext.request.getAttribute("resume").email}"></p>
            </td>
        </tr>
        <tr>
            <td align="right" height="40px">工作经验：</td>
            <td>
                <p><input type="text" name="experience"
                          value="${pageContext.request.getAttribute("resume").job_experience}"></p>
            </td>
        </tr>
        <tr>
            <td>&nbsp</td>
            <td>&nbsp</td>
        </tr>
    </table>
    <p align="center">
        <input type="submit" value="  保存修改  ">
    </p>

</form>

<%
    Resume resume = (Resume) request.getAttribute("resume");
    request.getSession().setAttribute("SESSION_RESUME", resume);
%>

<p align="center">
    <a href="${pageContext.request.contextPath}/Applicant/uploadResumePic.jsp">更换头像</a>
</p>


</body>
</html>
