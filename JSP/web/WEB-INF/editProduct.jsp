<%-- 
    Document   : editProduct
    Created on : Nov 13, 2016, 1:58:30 PM
    Author     : rotal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Edit Product</title>
	<link rel="stylesheet" type="text/css" href="asset/css/dashboard.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="asset/js/javascript.js"></script>
</head>
<body>
	<div class="container">
		<center>
			<h1 class="logo">
				<span class="sale">Sale</span><span class="project">Project</span>
			</h1>
		</center>
		<div id="userLogOut">	
			<b>Hi, <% out.print((String) request.getAttribute("username"));%>!</b><br>
			<b><a id="logoutButton" href="Logout">logout</a></b>
		</div>
		<ul class="navig">
                    <li><a href="Catalog">Catalog</a></li>
                    <li><a href="YourProduct">Your Product</a></li>
                    <li><a href="AddProduct">Add Product</a></li>
                    <li><a href="Sales">Sales</a></li>
                    <li><a href="Purchases">Purchases</a></li>
		</ul>
		<h1>Please update your product here</h1>
		<hr>
		
	<div class="detailProduct">
		<form id="myFormEditProduct" action="Edit" method="post">
			<b>Name</b>
			<span id="requiredEditProductName" class="tooltip">Required</span><br>
			<input id="name" type="text" name="name" value="<%out.print((String)request.getAttribute("name"));%>" oninput="inputValid('name', 'requiredEditProductName')">
				
			<b>Description (max 200 chars)</b>
			<span id="requiredEditProductDescription" class="tooltip">Required</span>
			<span id="maxDescriptionEditProduct" class="tooltip">max</span><br>
			<textarea id="description" name="description" oninput="inputValid('description', 'requiredEditProductDescription')" onkeydown="inputDescription200('description', 'maxDescriptionEditProduct')" onkeyup="inputDescription200('description', 'maxDescriptionEditProduct')"> <%out.print((String)request.getAttribute("description"));%></textarea>
				
			<b>Price (IDR)</b>
			<span id="requiredEditProductPrice" class="tooltip">Required</span>
			<span id="requiredEditProductPriceNumber" class="tooltip numberonly">Not valid</span><br>
			<input id="price" type="text" name="price" value="<%out.print((String)request.getAttribute("price"));%>" onchange="inputNumberValid('price', 'requiredEditProductPriceNumber')" oninput="inputValid('price', 'requiredEditProductPrice')">
				
			<b>Photo</b>
			<span id="requiredEditProductPhoto" class="tooltip">Required</span><br>
			<input type="file" name="photo" value="" disabled>
			<br>
			<br>

			
			<input type="hidden" value="<%out.print((String)request.getAttribute("idProduk"));%>" name="idProduk">
			<input type="submit" name="btnEdit" value="CANCEL" >
			<input type="submit" name="btnEdit" value="UPDATE" onclick ="validationEditProductButton(event)">
		</form>
	</div>

		
	</div>
</body>
</html>