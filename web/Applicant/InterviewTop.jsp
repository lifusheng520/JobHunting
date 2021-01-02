<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2021-1-1
  Time: 下午 08:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        .top{
            width: 100%;
            height:80px;
            background-color: #12ADA9;
        }

        .top>.personal{
            display: inline-block;
            float: right;

        }
        .top>.personal>div{
            width: 80px;
            height: 60px;
            line-height: 60px;
            text-align: center;
            float: right;
            margin-right: 15px;
            border: white 2px solid;
            border-radius: 15%;
            margin-top: 10px;
            vertical-align: middle;
        }

        a {
            text-decoration: none;
        }

        a:link {
            color: white;
        }

        a:visited {
            color: white;
        }

        a:hover {
            color: #33ee8c;
        }

        a:active {
            color: white;
        }
    </style>
</head>
<body>

<div class="top">
    <div class="personal">
        <div><a href="${pageContext.request.contextPath}/ExitServlet">退出</a></div>
        <div><a href="${pageContext.request.contextPath}/AddCompanyServlet?method=find">我的招聘</a></div>
        <div style="width: 130px"><a href="${pageContext.request.contextPath}/RecruitApplyServlet?method=find">查看递交简历</a></div>

    </div>
</div>

</body>
</html>
