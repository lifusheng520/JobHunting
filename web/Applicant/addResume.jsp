<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2020-12-28
  Time: 下午 03:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>resume</title>
</head>
<style type="text/css">
    .bk_box {
        position: absolute;
    }

    .bk_box > .table {
        margin-top: 300px;
        vertical-align: middle;
    }
</style>
<body>

<div class="bk_box" style="background-image: url('../img/img_index/bk_content_top.png');width: 100%;
    height: 900px; background-color: #12ADA9;">
    <div class="table">


        <form action="${pageContext.request.contextPath}/AddResumeServlet?method=add" method="post">
            <table border="1px solid black" bgcolor="#f0f8ff" align="center">
                <tr>
                    <td colspan="3"><h1>添加简历</h1></td>
                </tr>
                <tr>
                    <td width="120px" align="right">真实姓名：</td>
                    <td width="120px" align="right">
                        <input type="text" maxlength="15" name="realname">
                    </td>
                </tr>
                <tr>
                    <td width="120px" align="right">性别：</td>
                    <td>
                        <input type="radio" name="gender" value="男">男
                        <input type="radio" name="gender" value="女">女
                    </td>
                </tr>
                <tr>
                    <td width="120px" align="right">出生日期：</td>
                    <td>
                        <input type="text" name="birthday" value="2000-01-01">
                    </td>
                </tr>
                <tr>
                    <td width="120px" align="right">住址：</td>
                    <td>
                        <input type="text" name="location">
                    </td>
                </tr>
                <tr>
                    <td width="120px" align="right">联系电话：</td>
                    <td>
                        <input type="text" name="telephone">
                    </td>
                </tr>
                <tr>
                    <td width="120px" align="right">邮箱：</td>
                    <td>
                        <input type="text" name="email">
                    </td>
                </tr>
                <tr>
                    <td width="120px" align="right" height="200px">工作经验：</td>
                    <td>
                        <textarea name="experience" cols="30" rows="13"></textarea>
                    </td>
                </tr>
                <tr style="border: none">
                    <td width="120px" align="center" style="border: none">
                        <input type="submit" value="提交">
                    </td>
                    <td align="center" style="border: none">
                        <input type="reset" value="重置">
                    </td>
                </tr>
            </table>

        </form>
    </div>

</div>

</body>
</html>
