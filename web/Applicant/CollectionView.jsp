<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2020-12-31
  Time: 上午 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/CssOfTop.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="top">
    <div class="personal">
        <div><a href="${pageContext.request.contextPath}/ExitServlet">退出</a></div>
        <div><a href="${pageContext.request.contextPath}/JobServlet">主页</a></div>
    </div>
</div>


<form action="${pageContext.request.contextPath}/CollectionServlet?method=delete" method="post">

    <br><br><br>

    <c:if test="${empty requestScope.collections}">
        <div style="width: 100%;" align="center">
            <img src="${pageContext.request.contextPath}/img/nothing.jpg">
        </div>
    </c:if>

    <c:if test="${not empty requestScope.collections}">
        <c:forEach items="${requestScope.collections}" var="job">

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
                    <table style="border: none">
                        <tr align="center" valign="middle">
                            <td>
                                <img height="80px" src="${pageContext.request.contextPath}/img/company-ico/${job.ico}"/>
                            </td>
                            <td>
                                    ${job.name}
                            </td>
                        </tr>
                    </table>
                </td>
                </thead>
                <tr align="left">
                    <td>
                        <input type="checkbox" name="delete_collection" value="${job.job_id}">删除收藏
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

    <br><br>

    <c:if test="${not empty requestScope.collections}">
        <p align="center">

            <input type="submit" value="   删除收藏   ">
        </p>
    </c:if>


</form>

</body>
</html>
