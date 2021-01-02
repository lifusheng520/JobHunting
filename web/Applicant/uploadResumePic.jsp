<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2020-12-30
  Time: 下午 07:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uploadPic</title>
    <link href="${pageContext.request.contextPath}/css/CssOfTop.css" rel="stylesheet" type="text/css"/>
</head>
<body background="${pageContext.request.contextPath}/img/img_index/bk_content_top.png" bgcolor="white">

<script type="text/javascript">
    function validate() {
        var headShot = document.getElementById("headShot");
        if (headShot.value == "") {
            alert("请选择要上传的头像！");
            headShot.focus();
            return false;
        }
        return true;
    }
</script>

<div class="top">
    <div class="personal">
        <div><a href="${pageContext.request.contextPath}/ExitServlet">退出</a></div>
        <div><a href="${pageContext.request.contextPath}/JobServlet">主页</a></div>
    </div>
</div>


<form action="${pageContext.request.contextPath}/uploadResumePicServlet" method="post" enctype="multipart/form-data" onsubmit="return validate()">
    <table align="center" bgcolor="gray" width="250px" style="margin-top: 150px">
        <tr align="center">
            <td width="205px" height="250px" valign="middle">
                <img src="${pageContext.request.contextPath}/Applicant/head-ico/${SESSION_RESUME.head_shot}" width="200px" height="200px">
            </td>
        </tr>
        <tr>
            <td height="50px">
                <input type="file" name="headShot" value="上传照片" accept="image/*">
            </td>
        </tr>
        <tr align="center">
            <td height="50px">
                <input type="submit" value=" 保存 ">
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                <input type="reset" value=" 重置 ">
            </td>
        </tr>

    </table>
</form>

</body>
</html>
