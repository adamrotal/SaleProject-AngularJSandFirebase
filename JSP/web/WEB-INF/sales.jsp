<%-- 
    Document   : sales
    Created on : Nov 13, 2016, 2:06:15 PM
    Author     : rotal
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Sales</title>
	<link rel="stylesheet" type="text/css" href="asset/css/dashboard.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
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
			<li><a class="active" href="Sales">Sales</a></li>
			<li><a href="Purchases">Purchases</a></li>
		</ul>
		<h1>Here are your sales</h1>
		<hr>
		
		<%
                    List<Map<String,String>> listProduct =(List<Map<String,String>>) request.getAttribute("listProduct");
                    for(Map<String,String> product : listProduct){
                %>
				<div class="sales">
					<b><% out.print(product.get("tanggal"));%></b><br>
					<hr>
					<table id="produk"  cellpadding="10">
						<tr>
							<td id="foto"><img class="fotoProduk" src="asset/gambar/<% out.print(product.get("gambar"));%>" alt="foto produk"></td>
							<td id="deskripsi">
								<font size="5"><b><% out.print(product.get("nameProduk"));%></b></font><br><br>
								<font size="5">IDR <% out.print(product.get("totalPrice"));%></font><br>
								<% out.print(product.get("kuantitas"));%> pcs<br>
								@IDR <% out.print(product.get("price"));%><br><br>
								bought by <b><% out.print(product.get("username"));%></b>
							</td>
							<td id="dataPurchase">
								Delivery to <b><% out.print(product.get("namaPembeli"));%></b><br>
								<% out.print(product.get("fullAddress"));%><br>
								<% out.print(product.get("postalCode"));%><br>
								<% out.print(product.get("phoneNumber"));%><br>
							</td>
						</tr>
					</table>
					<hr>
				</div>
                <% 
                    }
                %>

		
	</div>
</body>
</html>