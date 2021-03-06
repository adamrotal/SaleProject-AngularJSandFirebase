
<html>
<head>
	<title>Login</title>
        <link rel="stylesheet" type="text/css" href="asset/css/frontPage.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="asset/js/jquery.js"></script>
        <script type="text/javascript" src="asset/js/javascript.js"></script>

</head>
<body>
    <% String message = (String) request.getAttribute("message");%>
	<div class="container">
		<center>
			<h1 class="logo">
				<span class="sale">Sale</span><span class="project">Project</span>
			</h1>
		</center>
		<h2>Please Login</h2>
		<hr>
                <% if(message != null) { %>
                <div id="errorMessage">
                    <% out.print(message); %>
                </div>
                <% } %>
                <form method="post" action="Login">
			<b>Email or Username</b>
			<span id="requiredLoginEmail" class="tooltip">Required</span><br>
			<input class="formValidation" type="text" name="email" oninput="inputValid('email', 'requiredLoginEmail')">
			<br>
			
			<b>Password</b>
			<span id="requiredLoginPassword" class="tooltip pass">Required</span><br>
			<input class="formValidation" type="password" name="password" oninput="inputValid('password', 'requiredLoginPassword')">
			<br>
			<br>
                        <input type="submit" value="LOGIN" <!--onclick="validationLoginButton(event)-->">
		</form>
		<br><br>
                <p><b>Don't have an account yet? Register <a class="btnHere" href="/JSP/Register"> here</a></b></p>
	</div>
</body>
</html>