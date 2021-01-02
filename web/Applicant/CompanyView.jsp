<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2021-1-1
  Time: 下午 08:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company info</title>
</head>

<c:if test="${requestScope.interview_update_state != null}">

    <c:if test="${requestScope.interview_update_state == 1}">
        <script>
            alert("修改成功！！");
        </script>
    </c:if>
    <c:if test="${requestScope.interview_update_state == 0}">
        <script>
            alert("修改失败！！");
        </script>
    </c:if>

</c:if>

<c:if test="${requestScope.interview_delete_state != null}">

    <c:if test="${requestScope.interview_delete_state == 1}">
        <script>
            alert("删除成功！！");
        </script>
    </c:if>
    <c:if test="${requestScope.interview_delete_state == 0}">
        <script>
            alert("删除失败！！");
        </script>
    </c:if>

</c:if>

<body background="${pageContext.request.contextPath}/img/bk_interview.jpg">

<%@include file="InterviewTop.jsp" %>

<c:if test="${empty requestScope.interviews || requestScope.interviews == null}">
    <div style="width: 100%;" align="center">
        <img src="${pageContext.request.contextPath}/img/nothing.jpg">
    </div>
</c:if>
<br><br>

<c:if test="${not empty requestScope.interviews}">

    <c:forEach items="${requestScope.interviews}" var="job">

        <form method="post">

            <table border="1px solid black" bgcolor="#f0f8ff" align="center" width="65%">
                <caption>
                    <h1>我的招聘信息</h1>
                </caption>
                <tr>
                    <td align="right">公司名称：</td>
                    <td align="left">
                        <table>
                            <tr>
                                <td>
                                    <input type="text" name="name" value="${job.name}">
                                </td>
                                <td>
                                    <img height="100px"
                                         src="${pageContext.request.contextPath}/img/company-ico/${job.ico}">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="right">公司福利信息：</td>
                    <td height="140px">
                        <textarea name="benefit_info" style="height: 135px;width: 100%">${job.benefit_info}</textarea>
                    </td>
                </tr>

                <tr>
                    <td align="right">工作名称：</td>
                    <td>
                        <input type="text" name="job_name" value="${job.job_name}">
                    </td>
                </tr>
                <tr>
                    <td align="right">薪资情况：</td>
                    <td>
                        <input type="text" name="job_salary" value=" ${job.job_salary}">
                    </td>
                </tr>
                <tr>
                    <td align="right">岗位信息：</td>
                    <td>
                        <textarea name="job_info" cols="30" rows="10">${job.job_info}</textarea>
                    </td>
                </tr>
                <tr>
                    <td align="right">工作地址：</td>
                    <td>
                        <input type="text" name="job_address" value="${job.job_address}">
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        当前状态：
                        <c:choose>
                            <c:when test="${job.state == 1}">正在招聘</c:when>
                            <c:when test="${job.state == 2}">已经暂停招聘</c:when>
                            <c:when test="${job.state == 3}">已经结束招聘</c:when>
                        </c:choose>
                    </td>
                    <td align="center">
                        重置状态：
                        <select name="state">
                            <c:choose>
                                <c:when test="${job.state == 1}">
                                    <option value="1" selected>开始招聘</option>
                                    <option value="2">暂停招聘</option>
                                    <option value="3">结束招聘</option>
                                </c:when>
                                <c:when test="${job.state == 2}">
                                    <option value="1">开始招聘</option>
                                    <option value="2" selected>暂停招聘</option>
                                    <option value="3">结束招聘</option>
                                </c:when>
                                <c:when test="${job.state == 3}">
                                    <option value="1">开始招聘</option>
                                    <option value="2">暂停招聘</option>
                                    <option value="3" selected>结束招聘</option>
                                </c:when>
                            </c:choose>
                        </select>
                    </td>
                </tr>

                <tr style="border: none">
                    <td colspan="2" width="120px" align="center" style="border: none">
                        <input formaction="${pageContext.request.contextPath}/AddCompanyServlet?method=update&job_id=${job.job_id}&company_id=${job.company_id}"
                               type="submit" value="   确认修改   ">
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <input formaction="${pageContext.request.contextPath}/AddCompanyServlet?method=delete&job_id=${job.job_id}&company_id=${job.company_id}"
                               type="submit" value="     删除     ">
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
