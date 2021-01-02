<%--
  Created by IntelliJ IDEA.
  User: 李福生
  Date: 2021-1-1
  Time: 下午 05:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Company</title>

    <style type="text/css">
        .bk_box {
            position: absolute;
        }

        .bk_box > .table {
            margin-top: 300px;
            vertical-align: middle;
        }
    </style>


</head>
<body>

<div class="bk_box" style="background-image: url('../img/img_index/bk_content_top.png');width: 100%;
    height: 900px; background-color: #12ADA9;">
    <div class="table">

        <form action="${pageContext.request.contextPath}/AddCompanyServlet?method=add" method="post">
            <table border="1px solid black" bgcolor="#f0f8ff" align="center">
                <tr>
                    <td colspan="2" align="left" height="50px"><h1>添加招聘基本信息</h1></td>
                </tr>
                <tr>
                    <td width="120px" align="right">公司名称：</td>
                    <td width="120px" align="left">
                        <input type="text" name="name" >
                    </td>
                </tr>
                <tr>
                    <td width="120px" align="right">公司福利信息：</td>
                    <td height="140px">
                        <textarea name="benefit_info" style="height: 135px;width: 100%"></textarea>
                    </td>
                </tr>

                <tr>
                    <td align="right">工作名称：</td>
                    <td>
                        <input type="text" name="job_name">
                    </td>
                </tr>
                <tr>
                    <td align="right">薪资情况：</td>
                    <td>
                        <input type="text" name="job_salary">
                    </td>
                </tr>
                <tr>
                    <td align="right">岗位信息：</td>
                    <td>
                        <textarea name="job_info" cols="30" rows="10"></textarea>
                    </td>
                </tr>
                <tr>
                    <td align="right">工作地址：</td>
                    <td>
                        <input type="text" name="job_address" value="上海·浦东新区">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <select name="state">
                            <option value="1">开始招聘</option>
                            <option value="2">暂停招聘</option>
                            <option value="3">结束招聘</option>
                        </select>
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
