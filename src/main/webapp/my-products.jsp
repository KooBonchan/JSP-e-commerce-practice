<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<c:set var="username" scope="page" value="${sessionScope.username }"></c:set>
<c:set var="permission" scope="page" value="${sessionScope.permission }"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FretBeat: Your first percussive experience</title>
    <link rel="stylesheet" href="style/theme.css">
</head>

<body>
	<header>
        <div class="logo">FretBeat</div>
        <div class="button-bar">
        	<div class="button" onclick="location.href='home'">home</div>
            <div class="profile">
                <div class="username">${username}</div>
                <div class="logout"><a href="home?logout=true">Logout</a></div>
            </div>
            
        </div>
    </header>

	<section>
		<div class="card-container">
		<figure class="product-card empty">
            <figcaption>
            <div class="add-new">
            <div style="font-size:100px;">+</div>
            <div>Add New Item</div>
            </div>
            </figcaption>
            <a href="product/product-create.jsp"></a>
		</figure>
        
		<c:forEach  var="product" items="${products}">
            <figure class="product-card"><img src="${product.imagePath}" alt="No Image" />
                <figcaption>
                    <h3>${product.name }</h3>
                    <p>Stock Left: ${product.inventory}</p>
                    <div class="price">
                        <fmt:formatNumber value="${product.price }" type="currency"
									currencySymbol="￦" groupingUsed="true" maxFractionDigits="0">
						</fmt:formatNumber>
                    </div>
                    <div class="button" onclick="location.href='product/update?id=${product.id}'">Edit your item</div>
                </figcaption>
            </figure>
        </c:forEach>
        </div>
	</section>
</body>
</html>