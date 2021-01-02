<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2021-1-2
  Time: 下午 04:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请</title>
</head>
<body>

<%@include file="InterviewTop.jsp" %>

<br><br><br>

<c:if test="${empty requestScope.applyInfos || requestScope.applyInfos == null}">
    <div style="width: 100%;" align="center">
        <img src="${pageContext.request.contextPath}/img/nothing.jpg">
    </div>
</c:if>

<c:if test="${not empty requestScope.applyInfos}">

    <c:forEach items="${requestScope.applyInfos}" var="applyInfo">

        <form action="${pageContext.request.contextPath}/RecruitApplyServlet ">

            <input type="hidden" name="method" value="updateReply">
            <input type="hidden" name="id" value="${applyInfo.resume_id}">
            <input type="hidden" name="job_id" value="${applyInfo.job_id}">

            <table border="2px gray solid" align="center" width="80%">
                <tr>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>出生日期</td>
                    <td>地址</td>
                    <td>电话</td>
                    <td>邮箱</td>
                    <td>工作经验</td>
                </tr>
                <tr>
                    <td>${applyInfo.realname}</td>
                    <td>${applyInfo.gender}</td>
                    <td>${applyInfo.birthday}</td>
                    <td>${applyInfo.location}</td>
                    <td>${applyInfo.telephone}</td>
                    <td>${applyInfo.email}</td>
                    <td>${applyInfo.job_experience}</td>
                </tr>
                <tr>
                    <td>回复ta：</td>
                    <td colspan="5">
                        <textarea name="reply_content" cols="60" rows="5" style="width: 100%">${applyInfo.reply}</textarea>
                    </td>
                    <td align="center">
                        <input type="submit" value="  回复  ">
                    </td>
                </tr>
            </table>
            <br>

        </form>

    </c:forEach>

</c:if>

<br><br><br>

</body>
</html>
