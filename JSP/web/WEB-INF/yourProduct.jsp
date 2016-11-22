<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Your Product</title>
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
			<li><a class="active" href="YourProduct">Your Product</a></li>
			<li><a href="AddProduct">Add Product</a></li>
			<li><a href="Sales">Sales</a></li>
			<li><a href="Purchases">Purchases</a></li>
		</ul>
		<h1>What are you going to sell today?</h1>
		<hr>
                
                
                <%
                    List<Map<String,String>> listProduct =(List<Map<String,String>>) request.getAttribute("listProduct");
                    for(Map<String,String> product : listProduct){
                %>
                    <div class="catalog">
			<b><% out.print(product.get("usernamePenjual"));%></b><br>
			added this on <% out.print(product.get("tanggalDiTambah"));%><br>
			<hr>
			<table id="produk"  cellpadding="10">
                            <tr>
				<td id="foto"><img class="fotoProduk" src="asset/gambar/<% out.print(product.get("gambar"));%>" alt="foto produk"></td>
				<td id="deskripsi">
                                    <font size="5"><b> <% out.print(product.get("name"));%></b></font><br><br>
                                    <font size="5">IDR <% out.print(product.get("price"));%></font><br>
                                    <% out.print(product.get("description"));%>
				</td>
				<td id="data">
                                    <% out.print(product.get("nLike"));%> likes <br>
                                    <% out.print(product.get("nSales"));%> purchase<br><br>
                                    <a id="editButton" href="Edit?idProduk=<% out.print(product.get("id"));%>"><b>EDIT<b></a>
                                    <a id="deleteButton" href="Delete?idProduk=<% out.print(product.get("id"));%>" onclick="validationDelete(this,event)"><b>DELETE<b></a>
				</td>
                            </tr>
			</table>
			<hr>
                        <div id="myDeleteModal" class="modal">
                            <div class="modal-content">
                                <span class="btnNoModal">no</span>
                                <span class="btnYesModal">yes</span>
                                <p>Do you really want to delete the form?</p>
                            </div>
                        </div>
                    </div>
                <% 
                    }
                %>

		
		


	</div>
</body>
</html>