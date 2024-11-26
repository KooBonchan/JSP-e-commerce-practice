<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update your product</title>
<link rel="stylesheet" href="/style/theme.css">
<link rel="stylesheet" href="/product/style/product-submit.css">
</head>
<body>
	<header><h1>Product Update</h1></header>
	<form action="update" method="post" name="formNewProduct" enctype="multipart/form-data"
		onsubmit="return validate(this);">
		<input type="hidden" name="id" value="${param.id}">		
		<table>
			<tr>
				<td><label for="product-name">Product name</label></td>
				<td><input name="product-name" value="${product.name}"></td>
			</tr>
			<tr>
				<td><label for="price">Price</label></td>
				<td><input name="price" type="number" value="${product.price}">￦</td>
			</tr>
			<tr>
				<td><label for="inventory">Quantity</label></td>
				<td><input name="inventory" type="number" value="${product.inventory}">개</td>
			</tr>
			<tr id="thumbnail-container" style="display:none">
				<td colspan=2>
				<div id="thumbnail">
				</div>
				</td>
			</tr>
			<tr>
			<td>Current image</td>
			<td><img src="../${product.imagePath}" alt="No Image"></td>
			</tr>
			<tr>
				<td><label for="image">Change to new image</label></td>
				<td><input type="file" name="image"></td>
			</tr>
			<tr>
				<td><label for="description">description</label></td>
				<td>
					<textarea rows="10" cols="40" name="description">${fn:trim(product.description)}</textarea>
				</td>
			</tr>
		</table>
		<div class="button-bar">
		<button type="submit" class="button">submit</button>
		<button type="reset" class="button">reset</button>
		</div>
	</form>
</body>
<script>
const form = document.formNewProduct;
function validate(form){
	return true;
}
</script>
</html>