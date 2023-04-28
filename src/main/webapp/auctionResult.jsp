<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrap">
  <c:if test="${fn:length(requestScope.map['end'])!=0}">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">拍卖结束的商品</h1>
    <div class="right rulse">当前用户是：<span class="blue strong">${sessionScope.user.userName}</span></div>
    <div class="cl"></div>
  </div>
<div class="items">
      <ul class="rows even strong">
        <li>名称</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="list-wd">成交价</li>
        <li class="borderno">买家</li>
      </ul>
      <c:forEach items="${requestScope.map['end'] }" var="end">
	      <ul class="rows">
	        <li>${end.auctionName }</li>
	        <li>${end.auctionStartTime }</li>
	        <li>${end.auctionEndTime}</li>
	        <li>${end.auctionStartPrice }</li>
	        <li class="list-wd">${end.auctionPrice }</li>
	        <li class="borderno red">${end.user.userName }</li>
	      </ul>
   	  </c:forEach>
  </div>
  </c:if>
  
  <c:if test="${fn:length(requestScope.map['notend'])!=0}">
  <h1>拍卖中的商品</h1>
  <div class="items records">
      <ul class="rows even strong rowh"> 
        <li>名称</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="borderno record">出价记录</li>
        <div class="cl"></div>
      </ul>
      	<c:forEach items="${requestScope.map['notend'] }" var="notend">
    <ul class="rows">
        <li>${notend.auctionName }</li>
        <li>${notend.auctionStartTime }</li>
        <li>${notend.auctionEndTime }</li>
        <li>${notend.auctionStartPrice }</li>
        <li class="borderno blue record">
        	<c:forEach items="${notend.auctionRecords }" var="record">
          		<p>${record.auctionUser.userName }&nbsp;&nbsp; ${record.auctionPrice }元</p>
          	</c:forEach>
        </li>
        <div class="cl"></div>
      </ul>
   		</c:forEach>
  </div>
  </c:if>
<!-- main end-->
</div>
</body>
</html>
