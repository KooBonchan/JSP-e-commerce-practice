<%@page import="com.ecommerce.dto.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../style/bootstrap.css">
<link rel="stylesheet" href="../style/darkmode.css">
</head>
<body>
	<header>
	<span style="cursor:pointer;" onclick="history.back()">←</span>
	cart<span id="cart">${fn:length(cart)}</span>
	</header>
	<h1>${product.name}</h1>
	<div class="image">
		<img src="../${product.imagePath}">
	</div>
	<div id="price-info">
		<span id="count-select">1개: </span> <span id="price">${product.price}</span>
	</div>
	<div id="provider">${product.providerName}</div>
	<div id="description">${product.description}</div>
	
	<input type="number" id="prod-count" value=1>
	<a class="btn btn-success" href="#" id="cart-submit-button">Add To Cart</a>
	
	<div>
		<div class="new-review">
			<span class="id">${username }</span>
			<span id="star-input">*i*n*p*u*t*</span>
			<span style="display:none" id="prod-id"></span>
			<textarea rows="5" cols="50" id="write-content"></textarea>
			<a class="btn btn-secondary" id="review-submit-button">Reply</a>
		</div>
		<!-- Somehow async using ajax -->
		<ul id="review-block">
			
		</ul>
	</div>
</body>
<script src="js/product-view.js"></script>
</html>