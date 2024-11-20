<%@page import="com.ecommerce.dto.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username = (String) session.getAttribute("username");
	boolean permission = (boolean) session.getAttribute("permission");
	ProductVO product = (ProductVO) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../style/darkmode.css">
</head>
<body>
	<header>
	</header>
	<%
	String imagePath = product.getImagePath();
	if(imagePath == null) imagePath = "noimage.gif";
	%>
	<h1><%=product.getName() %></h1>
	<div class="image">
		<img src="upload/<%=imagePath %>">
	</div>
	<div id="price-info">
		<span id="count-select">1개: </span> <span id="price"><%=product.getPrice() %></span>
	</div>
	<div id="provider"><%=product.getProvider() %></div>
	<div id="description"><%=product.getDescription() %></div>
	<div>
		<div class="new-review">
			<span class="id">id</span>
			<span id="star-input">*i*n*p*u*t*</span>
			<span style="display:none" id="prod-id"></span>
			<textarea rows="5" cols="50" id="write-content"></textarea>
			<button type="button" id="review-submit-button">submit</button>
		</div>
		<!-- Somehow async using ajax -->
		<ul id="review-block">
			<li>
			<div class="review">
				<span class="id">id</span>
				<span class="star">*****</span>
				<span class="content">content</span>
			</div>
			</li>
		</ul>
	</div>
</body>
<script src="./js/product-view.js"></script>
</html>