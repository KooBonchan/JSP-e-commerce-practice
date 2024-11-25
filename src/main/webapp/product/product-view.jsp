<%@page import="com.ecommerce.dto.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/style/theme.css">
<link rel="stylesheet" href="/product/style/product.css">
</head>
<body>
	<header>
	<span style="cursor:pointer;" onclick="history.back()" id="back-button">←</span>
	<span>cart(<span id="cart">${fn:length(cart)}</span>)</span>
	</header>
	<section class="product-container">
		<h1>${product.name}</h1>
		<div class="image">
			<img src="../${product.imagePath}" alt="No Image">
		</div>
		<div id="price-info">
			<span id="price">
				<fmt:formatNumber type="currency" currencySymbol="￦"
					maxFractionDigits="0" value="${product.price}"></fmt:formatNumber>
			</span>
			<span id="count-select">for each</span>
		</div>
		<div id="provider">Provider : ${product.providerName}</div>
		<div id="description">${product.description}</div>

		<div id="add-to-cart-container">
			<input type="number" id="prod-count" value=1 placeholder="0">
			<div class=button id="cart-submit-button">Add to Cart</div>
		</div>
		

	</section>

	<section class="review-container">
		<div class="new-review">
			<span class="id">${id }</span>
			<span style="display:none" id="prod-id"></span>
			<textarea id="write-content" placeholder="Leave comments..."></textarea>
			<div class=button id="review-submit-button">reply</div>
		</div>
		
		<!-- AJAX fetching for reviews-->
		<ul id="review-block">
			
		</ul>
	</section>
	
</body>
<script src="/product/js/product-view.js"></script>
</html>