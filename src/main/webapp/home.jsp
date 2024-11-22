<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<c:set var="username" scope="page" value="${sessionScope.username }"></c:set>
<c:set var="permission" scope="page" value="${sessionScope.permission }"></c:set>
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
		<h3>Hello, ${username}</h3>
		<div class="button-bar" style="margin-bottom:10px;">
			<c:if test="${permission}">
				<a class="add-product btn btn-success" href="product/product-create.jsp">add product</a>
				<a class="btn btn-success"
					href="${param.my=='true'?'home'       :'home?my=true'}">
						  ${param.my=='true'?'Home(admin)':'My Products(admin)'}
				</a>
			</c:if>
			
			<a href="#" class="btn btn-success" id="buy-cart" onclick="buyAll()">
				Buy All (<span id="cart">${fn:length(cart)}</span>)
			</a>
			<a class="btn" href="home?logout=true" id="logout">logout</a>
		</div>
	</header>
	<section>
		<div id="products" class="row list-group">
			<c:forEach  var="product" items="${products}">
				<div class="item col-xs-4 col-lg-4 list-group-item" onclick="open_product(${product.id })">
					<div class="thumbnail">
						<img src="${product.imagePath }">
					</div>
					<div class="row">
						<div class="caption">
							<div class="group inner list-group-item-heading">${product.name }</div>
							<p class="group inner list-group-item-text">${product.description }</p>
						</div>
						<div class="list-group-item-block">
							<p class="lead">
								<fmt:formatNumber value="${product.price }" type="currency"
									currencySymbol="￦" groupingUsed="true" maxFractionDigits="0"></fmt:formatNumber>
							</p>
								<c:if test="${permission && param.my=='true'}">
									<p class="inventory">${product.inventory}</p>
									<a class="add-product btn btn-secondary" href="product/product-update.jsp">Update</a>
									<a class="add-product btn btn-danger" href="#">Delete</a>
								</c:if>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</section>
</body>
<script>
const cart = document.getElementById('cart');

window.onload = pageLoadHandler;
document.addEventListener("visibilitychange", function(){
	if (document.visibilityState === "visible") {
		pageLoadHandler()
    }
});


function pageLoadHandler(){
	//currently single function, though leave space for future expandability
	updateCart();
}
function open_product(id){
	location.href = "./product/item?id=" + id;
}
function updateCart(){
	fetch('/cart-count')
	.then(response => response.json())
	.then(data => {
		document.getElementById('cart').innerHTML = data.count;
	})
	.catch(e=>console.log(e))
}
function buyAll(){
	if(cart.value == 0){
		return;
	}
	later(1000)
	.then(next_url=>"/buy-cart")
	.then(fetch)
	.then(ignored=>updateCart())
	.then(ignored=>alert("Payment is done successfully.\nSomehow, by somebody?")); 
}

function later(delay) {
    return new Promise(function(resolve) {
    	setTimeout(() => resolve(), delay);
    });
}

</script>
</html>