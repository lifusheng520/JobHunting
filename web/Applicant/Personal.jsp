<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2020-12-31
  Time: 上午 08:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal</title>
    <link href="css/CssOfTop.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript">
        function hiddenPersonal() {
            var box = document.querySelector(".personal.info");
            box.style.display = 'none';
            var box2 = document.querySelector(".update.password");
            box2.style.display="";
        }

        function cancelHidden() {
            var box = document.querySelector(".personal.info");
            box.style.display = "";
            var box2 = document.querySelector(".update.password");
            box2.style.display="none";
        }

    </script>


    <c:choose>
        <c:when test="${updateState == 1}">
            <script type="text/javascript">
                alert("修改成功！！！");
            </script>
        </c:when>
        <c:when test="${updateState == 0}">
            <script type="text/javascript">
                alert("数据库错误，修改失败！！！");
            </script>
        </c:when>
        <c:when test="${updateState == 2}">
            <script type="text/javascript">
                alert("两次密码不一致！！！");
            </script>
        </c:when>
    </c:choose>

</head>
<body>

<div class="top">
    <div class="search">
        <p align="left" style="font-size: 30px;color:orangered;width: 600px">用户 ${USERINFO.account} 你好！</p>
    </div>

    <div class="personal">
        <div><a href="${pageContext.request.contextPath}/ExitServlet">退出</a></div>
        <div><a href="${pageContext.request.contextPath}/JobServlet">主页</a></div>
    </div>

</div>

<div class="personal info" style="margin-top: 150px;">
    <table width="60%" style="margin-left: auto;margin-right: auto;background-color: aqua">
        <tr>
            <td><h1>个人信息</h1></td>
        </tr>
        <tr>
            <td align="center">
                <img src="${pageContext.request.contextPath}/Applicant/head-ico/${RESUME.head_shot}">
            </td>
        </tr>
        <tr>
            <td align="center" height="40px">
                <p>${RESUME.realname}</p>
            </td>
        </tr>
        <tr>
            <td align="center" height="40px"><p>${RESUME.location}</p></td>
        </tr>
        <tr>
            <td align="center" height="40px"><p>${RESUME.telephone}</p></td>
        </tr>
        <tr>
            <td align="center" height="40px"><p>${RESUME.email}</p></td>
        </tr>
        <tr>
            <td>&nbsp</td>
        </tr>
        <tr>
            <td align="right">
                <a href="javascript: hiddenPersonal();">修改密码</a>
            </td>
        </tr>
    </table>
</div>

<form action="${pageContext.request.contextPath}/PersonalServlet?method=write" method="post">
    <div class="update password"
         style="margin-top: 200px;margin-left: auto;margin-right: auto;background-color: aqua;width: 50%;text-align: center;display: none;">
        <br>
        <br>
        <div>账号：<input type="text" name="account" value="${USERINFO.account}"></div>
        <br>
        <div>密码：<input type="password" name="password1"></div>
        <br>
        <div>确认：<input type="password" name="password2"></div>
        <br>
        <div>
            <input type="submit" value="  确认修改  ">
            &nbsp&nbsp&nbsp&nbsp
            <input type="button" onclick="cancelHidden()" value="  取消  ">
        </div>
        <br>
        <br>
    </div>
</form>

</body>
</html>
