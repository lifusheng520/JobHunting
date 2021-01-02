<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<link href="css/CssOfTop.css" rel="stylesheet" type="text/css"/>
<body>



<script type="text/javascript">
    function goSearch(){
        var text = document.querySelector(".top>.search #text");
        var select = document.querySelector(".top>.search select");
        if(text.value.length == 0 && select.selectedIndex == 0) {
            alert("请输入搜索内容 或 选择公司！");
            return false
        }
        else{
            return true;
        }
    }
</script>

<div class="top">
    <div class="search">
        <form action="${pageContext.request.contextPath}/SearchApplyServlet?method=search" method="post" onsubmit="return goSearch()">

            <select name="company_select_id" style="width: 60px;">
                <option disabled selected>公司</option>

                <c:if test="${not empty sessionScope.SESSION_COMPANYS}">

                    <c:forEach items="${sessionScope.SESSION_COMPANYS}" var="companys">

                        <option value="${companys.company_id}">${companys.name}</option>

                    </c:forEach>
                </c:if>

            </select>

            <input type="text" id="text" name="apply_search" placeholder="岗位搜索" style="height: 60px;margin-top: 10px">
            <input type="submit" value=" 搜索 " style="height: 50px;margin-top: 10px;border-radius:50%;">
        </form>
    </div>

    <div class="personal">
        <div><a href="${pageContext.request.contextPath}/PersonalServlet?method=read">个人中心</a></div>
        <div><a href="${pageContext.request.contextPath}/AddResumeServlet?method=find">简历</a></div>
        <div><a href="${pageContext.request.contextPath}/CollectionServlet?method=view">我的收藏</a></div>
        <div><a href="${pageContext.request.contextPath}/JobServlet">主页</a></div>
        <div>
            <a href="${pageContext.request.contextPath}/JobApplyServlet?method=find&user_id=${SESSION_USER.id}">我的申请</a>
        </div>


    </div>



</div>


</body>
</html>
