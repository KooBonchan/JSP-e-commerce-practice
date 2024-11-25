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
        	<c:if test="${permission}">
            	<div class="button">My Products (Admin)</div>
            </c:if>
            <div class="button" id="buy-cart" onclick="buyAll()">Buy my cart (<span id="cart">${fn:length(cart)}</span>)</div>
            <div class="profile">
                <div class="username">${username}</div>
                <div class="logout"><a href="home?logout=true">Logout</a></div>
            </div>
            
        </div>
    </header>

	<section>
		<div class="card-container">
		<c:forEach  var="product" items="${products}">
            <figure class="product-card"><img src="${product.imagePath}" alt="No Image" />
                <figcaption>
                    <h3>${product.name }</h3>
                    <p>${product.description}</p>
                    <div class="price">
                        <fmt:formatNumber value="${product.price }" type="currency"
									currencySymbol="￦" groupingUsed="true" maxFractionDigits="0">
						</fmt:formatNumber>
                    </div>
                </figcaption><a href="#"></a>
            </figure>
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