<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<% String keyword = (String) request.getAttribute("keyword"); %>
<% String category = (String) request.getAttribute("category"); %>
<!DOCTYPE html>
<html ng-app="chatApp">
<head>
	<title>Catalog</title>
	<link rel="stylesheet" type="text/css" href="asset/css/dashboard.css">
	<link rel="stylesheet" type="text/css" href="asset/css/chat.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
        
        <script type="text/javascript" src="asset/js/angular.min.js"></script>
        <!--<script type="text/javascript" src="asset/js/angular-sanitize.min.js"></script>-->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.min.js"></script>-->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular-sanitize.js"></script>-->
        <script type="text/javascript" src="asset/js/chat.js"></script>
        <script type="text/javascript" src="asset/js/ng-chat.js"></script>
        
	
</head>
<body ng-controller="chatCtrl">
        <div class="chatbox" ng-show="chatShow">
            <div class="chatheader">
                    <span class="user-status online"></span>
                    <b>{{ username }}</b>
                    <button class="close" ng-click="chatShow=!chatShow">&#10006;</button>
            </div>

            <div class="chatlogs" ng-bind-html="chatData">
            </div>
            
            <form ng-submit="inputChat = null">
                <div class="chat-form">
                    <input type="text" name="msg" ng-model="inputChat" id="inputChat">
                    <button class="buttonChat" ng-click="send()">send</button>
                </div>
            </form>
        </div>
    
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
			<li><a class="active" href="Catalog">Catalog</a></li>
			<li><a href="YourProduct">Your Product</a></li>
			<li><a href="AddProduct">Add Product</a></li>
			<li><a href="Sales">Sales</a></li>
			<li><a href="Purchases">Purchases</a></li>
		</ul>
		<h1>What are you going to buy today?</h1>
		<hr>

		<form action="Catalog" method="get">
			<div class="searchbox">	
		        <input type="text" placeholder="Search catalog..." name="keyword" value="<% out.print(keyword); %>" required>
		        <input id="searching" type="submit" value="GO">
			</div>
			<div class="byRadio">
				<div id="by">by</div><br>
                                <input type="radio" name="category" value="product" <% if(category.equals("product")) out.print("checked");%>> product<br>
					<input type="radio" name="category" value="store" <% if(category.equals("store")) out.print("checked");%>> store<br>
					
			</div>	
		</form>
		
                                        
                <%
                    List<Map<String,String>> listProduct =(List<Map<String,String>>) request.getAttribute("listCatalog");
                    for(Map<String,String> product : listProduct){
                %>
                    <div class="catalog">
			<span class="user-status offline"></span>
                        <a class="username" ng-click="clickChat('<% out.print(product.get("usernamePenjual"));%>')" href="javascript:void(0);"><b><% out.print(product.get("usernamePenjual"));%></b></a><br>
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
                                    <span class="idProdukData"><% out.print(product.get("id"));%></span>
                                    <span class="nLike"><% out.print(product.get("nLike"));%></span> likes <br>
                                    <% out.print(product.get("nSales"));%> purchase<br><br>
                                    <% if (product.get("liked").equals("0")){%>
                                        <a class="likeButton" href="Like?idProduk=<% out.print(product.get("id"));%>" >LIKE</a>
                                    <%} else {%>
                                        <a class="likeButton dislikeButton" href="Like?idProduk=<% out.print(product.get("id"));%>" >LIKED</a>
                                    <%}%>
                                    <a id="buyButton" href="Buy?idProduk=<% out.print(product.get("id"));%>">BUY</a>
				</td>
                            </tr>
			</table>
			<hr>
                    </div>
                <% 
                    }
                %>
                
	</div>
        <script src="asset/js/firebase.js"></script>
	<script src="asset/js/chat-handler.js"></script>
</html>