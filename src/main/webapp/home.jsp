<%@page import="javax.tools.DocumentationTool.Location"%>
<%@page import="com.ecommerce.dto.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username = (String) session.getAttribute("username");
	boolean permission = (boolean) session.getAttribute("permission");
	List<ProductVO> products = (List<ProductVO>)request.getAttribute("products");
	if(products == null) {
		throw new RuntimeException("Unsecure coding");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="./style/home.css">
<link rel="stylesheet" href="./style/darkmode.css">
</head>
<body>
	<header>
	<!-- 공유? -->
		<h3>Hello, <%=username %></h3>
		<div class="button-bar">
			<ul>
				<% if(permission){ %>
					<li>my products(admin)</li>	
				<% } %>
				<li>my reviews</li>
				<li><a href="home?logout=true" >logout</a></li>
			</ul>
		</div>
	</header>
	<section>
		<div class="flex-container" id="container">
			<% for(ProductVO product : products){
				String imagePath = product.getImagePath();
				if(imagePath == null) imagePath = "noimage.gif";
				%><div class="item" onclick="open_product(<%=product.getId()%>)">
					<div class="thumbnail"><img src="upload/<%=imagePath %>"></div>
					<!--image-->
					<div class="name"><%=product.getName() %></div>
					<div class="price"><%=product.getPrice() %></div>
				</div><%
				
			} %>
		</div>
	</section>
</body>
<script>
function open_product(id){
	location.href = "./product?id=" + id;
}
</script>
</html>