<%-- 
    Document   : confirmationPurchase
    Created on : Nov 13, 2016, 1:56:29 PM
    Author     : rotal
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Confirm Purches</title>
	<link rel="stylesheet" type="text/css" href="asset/css/dashboard.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="asset/js/javascript.js"></script>
</head>
<body>
        <% List<String> produk = (List<String>)request.getAttribute("produk"); %>
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
		<h1>Please confirm your purchase</h1>
		<hr>
		<form id="myFormConfirmation_Purchase" action="Buy" method="post">
			<div class="remainder">	
                            <span class="product">Product</span>  : <%out.print(produk.get(0));%><br>
				<input id="initialPrice" type="hidden" name="initPrice" value="<%out.print(produk.get(2));%>"> 
				<span class="price">Price</span> : IDR <span id="aPrice"><?php echo number_format($produk['price']);?></span><br>
				<span class="qual">Quantity</span> : <input id="quantity" type="number" value="1" name="kuantitas" oninput="countPrice()"> PCS<br>
				<span class="totalprice">Total Price</span> : IDR <span id="totalPrice"><%out.print(produk.get(2));%></span><br>
				<span class="del">Delivery To</span> : <br>
			</div>
			<br>
                        <input type="hidden" name="namaProduk" value="<%out.print(produk.get(0));%>">
                        <input type="hidden" name="gambar" value="<%out.print(produk.get(4));%>">
			<div class="confirm">
				Consignee
				<span id="requiredConfirmPurchaseConsignee" class="tooltip">Required</span><br>
				<input id="consignee" type="text" name="namaPembeli" value="<%out.print((String)request.getAttribute("fullName"));%>" oninput="inputValid('namaPembeli', 'requiredConfirmPurchaseConsignee')"><br>
				<br>
				
				Full Address
				<span id="requiredConfirmPurchaseFullAddress" class="tooltip">Required</span><br>
				<textarea id="address" name="fullAddress" oninput="inputValid('fullAddress', 'requiredConfirmPurchaseFullAddress')"><%out.print((String)request.getAttribute("fullAddress"));%></textarea>
				
				Postal Code
				<span id="requiredConfirmPurchasePostalCode" class="tooltip">Required</span><br>
				<input id="postal" type="text" name="postalCode" value="<%out.print((String)request.getAttribute("postalCode"));%>" oninput="inputValid('postalCode', 'requiredConfirmPurchasePostalCode')"><br>
				<br>
				
				Phone Number
				<span id="requiredConfirmPurchasePhoneNumber" class="tooltip">Required</span>
				<span id="requiredConfirmPurchasePhoneNumberOnly" class="tooltip">Number only</span><br>
				<input id="phone" type="text" name="phoneNumber" value="<%out.print((String)request.getAttribute("phoneNumber"));%>" onchange="inputNumberValid('phoneNumber', 'requiredConfirmPurchasePhoneNumberOnly')" oninput="inputValid('phoneNumber', 'requiredConfirmPurchasePhoneNumber')"><br>
				<br>
				
				12 Digits Credit Card Number
				<span id="requiredConfirmPurchaseCreditCard" class="tooltip">Required</span>
				<span id="requiredConfirmPurchaseCreditCard12" class="tooltip">Must be consist of 12 digits number</span><br>
				<input id="credit" type="text" name="creditCard" onchange="validationCreditCard()" oninput="inputValid('creditCard', 'requiredConfirmPurchaseCreditCard')">
				<br>
				<br>
				
				3 Digit Card Verification Value
				<span id="requiredConfirmPurchaseDigitCard" class="tooltip">Required</span>
				<span id="requiredConfirmPurchaseDigitCard3" class="tooltip">Must be consist of 3 digits number</span><br>
				<input id="verification" type="text" name="codeVerification" onchange="validationVerification()" oninput="inputValid('codeVerification', 'requiredConfirmPurchaseDigitCard')">
				<br><br>
				<br>
                                <input type="hidden" name="idProduk" value="<%out.print((String)request.getAttribute("idProduk"));%>">
				<input type="hidden" name="idPenjual" value="<%out.print(produk.get(3));%>">
				<button id="btnCancel" value="CANCEL" name="action">CANCEL</button>
                                <button id="btnConfirm" value="CONFIRM" name="action" <!--onclick="validationConfirmPurchaseButton(event)-->">CONFIRM</button>
				<div id="myPurchaseModal" class="modal">
					<div class="modal-content">
					    <span class="btnNoModal">no</span>
					    <span class="btnYesModal">yes</span>
					    <p>Do you really want to submit the form?</p>
					</div>

				</div>
			
			</div>
		</form>
	</div>
</body>
</html>