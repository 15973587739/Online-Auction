<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function goToPage(pageIndex){
		document.forms[0].action=document.forms[0].action+"?pageIndex="+pageIndex;
		document.forms[0].submit();
	}
</script>
</head>

<body>
<form method="post" action="do/auction/auctionList">
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
  	<c:if test="${not empty sessionScope.user}">
    	<div class="logout right"><a href="do/user/doLogout" title="注销">注销</a></div>
    </c:if>
    <c:if test="${empty sessionScope.user}">
    	<div class="logout right"><a href="login.jsp" title="登录">登录</a></div>
    </c:if>
  </div>
  <div class="forms">
    <label for="name">名称</label>
    <input name="auctionName" type="text" class="nwinput" id="name"/>
    <label for="names">描述</label>
    <input name="auctionDesc" type="text" id="names" class="nwinput"/>
    <label for="time">开始时间</label>
    <input name="auctionStartTime" type="text" id="time" class="nwinput"/>
    <label for="end-time">结束时间</label>
    <input name="auctionEndTime" type="text" id="end-time" class="nwinput" />
    <label for="price">起拍价</label>
    <input name="auctionStartPrice" type="text" id="price" class="nwinput" />
    <input type="submit"  value="查询" class="spbg buttombg f14  sale-buttom"/>
    <c:if test="${sessionScope.user.userIsAdmin}">
    	<input type="button" onclick="location='addAuction.jsp'"  value="发布" class="spbg buttombg f14  sale-buttom buttomb"/>
    </c:if>
    <br/>
    <c:if test="${not sessionScope.user.userIsAdmin}">
      &nbsp;&nbsp;&nbsp;&nbsp;<a href="do/auction/auctionResult"><b>查看竞拍结果</b></a>
      </c:if>
  </div>
  <div class="items">
      <ul class="rows even strong">
        <li>名称</li>
        <li class="list-wd">描述</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="borderno">操作</li>
      </ul>
      <c:forEach items="${requestScope.auctionPageInfo.pageList }" var="auction">
      <ul class="rows">
        <li>${auction.auctionName }</li>
        <li class="list-wd">${auction.auctionDesc }</li>
        <li>${auction.auctionStartTime }</li>
        <li>${auction.auctionEndTime }</li>
        <li>${auction.auctionStartPrice }</li>
        <li class="borderno red">
        	<c:if test="${sessionScope.user.userIsAdmin}">
           		修改|删除
          	</c:if>
        	<c:if test="${not sessionScope.user.userIsAdmin}">
          		<a href="do/auction/auctionDetail?auctionId=${auction.auctionId }">竞拍</a>
          	</c:if>
        </li>
      </ul>
      </c:forEach>
      <div class="page">
        <a href="javascript:goToPage(1)">首页</a>
        <c:if test="${requestScope.auctionPageInfo.pageIndex!=1}">
        	<a href="javascript:goToPage(${requestScope.auctionPageInfo.pageIndex-1 })">上一页</a>
        </c:if>
        <c:forEach step="1" begin="1" end="${requestScope.auctionPageInfo.totalPages }" var="pageIndex">
        <a href="javascript:goToPage(${pageIndex })">${pageIndex }</a>
        </c:forEach> 
        <c:if test="${requestScope.auctionPageInfo.pageIndex!=requestScope.auctionPageInfo.totalPages}">
        	<a href="javascript:goToPage(${requestScope.auctionPageInfo.pageIndex+1 })">下一页</a>
        </c:if>
        <a href="javascript:goToPage(${requestScope.auctionPageInfo.totalPages })" >尾页</a> 
      </div>
  </div>
<!-- main end-->
</div>
</form>
</body>
</html>
