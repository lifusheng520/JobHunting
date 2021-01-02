<%@ page import="bean.Job" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2020-12-31
  Time: 下午 03:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ApplyView</title>
    <link href="css/CssOfTop.css" rel="stylesheet" type="text/css"/>

    <c:choose>

        <c:when test="${apply_delete_state == 1}">
            <script type="text/javascript">
                alert("删除成功！！！");
            </script>
        </c:when>
        <c:when test="${apply_delete_state == 0}">
            <script type="text/javascript">
                alert("删除失败！！！");
            </script>
        </c:when>

    </c:choose>

</head>
<body>

<div class="top">
    <div class="personal">
        <div><a href="${pageContext.request.contextPath}/ExitServlet">退出</a></div>
        <div><a href="${pageContext.request.contextPath}/JobServlet">主页</a></div>
    </div>
</div>

<%
    List<Job> jobList = (List<Job>) request.getAttribute("jobList");
    request.setAttribute("jobList", jobList);
%>

<br><br>

<c:if test="${empty requestScope.jobList || requestScope.jobList == null}">
    <div style="width: 100%;" align="center">
        <img src="${pageContext.request.contextPath}/img/nothing.jpg">
    </div>
</c:if>

<c:if test="${not empty requestScope.jobList}">

    <c:forEach items="${requestScope.jobList}" var="job">

        <table align="center" border="1px gray solid" width="90%">
            <thead>
            <td width="45%" style="font-size: 25px;color:red;">${job.job_name}
                &nbsp&nbsp&nbsp&nbsp&nbsp
                <span style="border: greenyellow 3px solid;text-align: right">
                <c:choose>
                    <c:when test="${job.state==1}">招聘中</c:when>
                    <c:when test="${job.state==2}">已暂停</c:when>
                    <c:when test="${job.state==3}">已结束</c:when>
                </c:choose>
                </span>
            </td>
            <td valign="middle">
                <table width="100%" style="border: none">
                    <tr align="right" valign="middle">
                        <td>
                            <img height="80px" src="${pageContext.request.contextPath}/img/company-ico/${job.ico}"/>
                        </td>
                        <td align="left">
                                ${job.name}
                        </td>
                        <td align="right" valign="middle">
                                <%--提交申请--%>
                            <a href="${pageContext.request.contextPath}/JobApplyServlet?method=delete&job_id=${job.job_id}&user_id=${SESSION_USER.id}">
                                <span style="color: red;font-size: 25px;border: 1px gray solid">删除申请</span>
                            </a>
                        </td>
                    </tr>
                </table>
            </td>
            </thead>
            <tr align="left">
                <td>
                    <p>${job.job_salary}</p>
                    <p>${job.job_info}</p>
                    <p>${job.job_address}</p>
                </td>
                <td>${job.benefit_info}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <span>申请回复：</span>
                    <span>${job.reply}</span>
                </td>
            </tr>
        </table>
        <br>

    </c:forEach>

</c:if>

</body>
</html>
