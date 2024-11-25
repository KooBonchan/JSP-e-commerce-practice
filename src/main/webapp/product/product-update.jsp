<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register new product</title>
<link rel="stylesheet" href="../style/darkmode.css">
</head>
<body>
	<h1>New Product</h1>
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
				<td><textarea rows="10" cols="40" name="description">"${product.description}"</textarea>
				</td>
			</tr>
		</table>
		<button type="submit">submit</button>
		<button type="reset">reset</button>
	</form>
</body>
<script>
const form = document.formNewProduct;
function validate(form){
	return true;
}
</script>
</html>