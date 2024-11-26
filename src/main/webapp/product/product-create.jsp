<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Register new product</title>
<link rel="stylesheet" href="/style/theme.css">
<link rel="stylesheet" href="/product/style/product-submit.css">
<style>
.button-bar{
	display:flex;
	flex-direction: row;
	justify-content: center;
	margin-top:20px;
}
button.button{
	font-family: Geist Mono, 'Courier New', Courier, monospace;
	border:none;
}
button.button:after{
	display:none;
}
</style>
</head>
<body>
	<header>
	<h1 style="width:fit-content">New Product</h1>
	</header>
	<form action="create" method="post" name="formNewProduct" enctype="multipart/form-data"
		onsubmit="return validate(this);">
		<table>
			<tr>
				<td><label for="product-name">Product name</label></td>
				<td><input name="product-name" required></td>
			</tr>
			<tr>
				<td><label for="price">Price</label></td>
				<td><input name="price" type="number" placeholder="10,000" required>￦</td>
			</tr>
			<tr>
				<td><label for="inventory">Quantity</label></td>
				<td><input name="inventory" type="number" placeholder="231" required>개</td>
			</tr>
			<tr id="thumbnail-container" style="display:none">
				<td colspan=2>
				<div id="thumbnail">
				</div>
				</td>
			</tr>
			<tr>
				<td><label for="image">upload image</label></td>
				<td><input type="file" name="image" required></td>
			</tr>
			<tr>
				<td><label for="description">description</label></td>
				<td><textarea rows="10" cols="40" name="description" placeholder="description..." required></textarea>
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