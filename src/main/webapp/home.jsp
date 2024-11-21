<%@page import="javax.tools.DocumentationTool.Location"%>
<%@page import="com.ecommerce.dto.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
boolean permission = (boolean) session.getAttribute("permission");
List<ProductVO> products = (List<ProductVO>) request.getAttribute("products");
if (products == null) {
	throw new RuntimeException("Unsecure coding");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="./style/home.css">
<link rel="stylesheet" href="./style/bootstrap.css">
<link rel="stylesheet" href="./style/darkmode.css">
</head>
<body>
	<header>
		<!-- 공유? -->
		<h3>
			Hello,
			<%=username%></h3>
		<div class="button-bar">
			<ul>
				<%
				if (permission) {
				%>
				<li>my products(admin)</li>
				<%
				}
				%>
				<li>my reviews</li>
				<li><a href="home?logout=true">logout</a></li>
			</ul>
		</div>
	</header>
	<section>
		<div id="products" class="row list-group">
			<%
			for (ProductVO product : products) {
				String imagePath = product.getImagePath();
				if (imagePath == null)
					imagePath = "placeholder.jpg";
				imagePath = "uploads/thumbnail/" + imagePath;
			%><div class="item col-xs-4 col-lg-4 list-group-item"
				onclick="open_product(<%=product.getId()%>)">
				<div class="thumbnail">
					<img src="<%=imagePath%>">
				</div>
				<div class="row">
					<div class="caption">
						<div class="group inner list-group-item-heading"><%=product.getName()%></div>
						<p class="group inner list-group-item-text"><%=product.getDescription()%></p>
					</div>
					<div class="list-group-item-block">
						<p class="lead">
							￦
							<%=product.getPrice()%></p>
						<a class="btn btn-success" href="#">Add to cart</a>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</section>
</body>
<script>
function open_product(id){
	location.href = "./product?id=" + id;
}
</script>
</html>