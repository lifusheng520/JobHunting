<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2021-1-1
  Time: 下午 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SearchResult</title>
</head>
<body>

<%@include file="top.jsp"%>

<br><br>

<form action="${pageContext.request.contextPath}/CollectionServlet?method=add" method="post">

    <c:if test="${not empty requestScope.searchJobs}">
        <c:forEach items="${requestScope.searchJobs}" var="job">

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
                                <a href="${pageContext.request.contextPath}/JobApplyServlet?method=add&job_id=${job.job_id}&user_id=${SESSION_USER.id}">
                                    <span style="color: red;font-size: 25px;border: 1px gray solid">提交申请</span>
                                </a>
                            </td>
                        </tr>
                    </table>
                </td>
                </thead>
                <tr align="left">
                    <td>
                        <input type="checkbox" name="collection" value="${job.job_id}">收藏
                        <p>${job.job_salary}</p>
                        <p>${job.job_info}</p>
                        <p>${job.job_address}</p>
                    </td>
                    <td>${job.benefit_info}</td>
                </tr>
            </table>
            <br>

        </c:forEach>
    </c:if>

    <p align="center">
        <input type="submit" value="   添加到我的收藏   ">
    </p>
</form>

<br><br><br>

</body>
</html>
