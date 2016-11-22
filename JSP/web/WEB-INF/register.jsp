<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="asset/css/frontPage.css">
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
		<h2>Please Register</h2>
		<hr>
		<form id="myFormRegister" action="/JSP/Register" method="post" name="registerForm">
			<b>Full Name</b>
			<span id="requiredRegisterFullName" class="tooltip">Required</span><br>
			<input class="formValidation" type="text" name="fullName" oninput="inputValid('fullName', 'requiredRegisterFullName')">
			<br>
			
			<b>Username</b>
			<span id="requiredRegisterUsername" class="tooltip">Required</span>
			<input class="formValidation" type="text" name="username" oninput="inputValid('username', 'requiredRegisterUsername')"><br>
			<br>
			
			<b>Email</b>
			<span id="requiredRegisterEmail" class="tooltip">Required</span>
			<span id="requiredRegisterEmailFormat" class="tooltip emailFormat">Email not valid</span><br>
			<input type="text" name="email" onchange="inputEmailValid('email', 'requiredRegisterEmailFormat')" oninput="inputValid('email', 'requiredRegisterEmail')">
			<br>
			
			<b>Password</b>
			<span id="requiredRegisterPassword" class="tooltip">Required</span><br>
			<input class="formValidation" type="password" name="password" oninput="inputValid('password', 'requiredRegisterPassword')">
			<br>
			
			<b>Confirm Password</b>
			<span id="requiredRegisterConfirmPass" class="tooltip">Required</span><br>
			<input class="formValidation" type="password" name="confirmpassword" oninput="inputValid('confirmpassword', 'requiredRegisterConfirmPass')">
			<br>
			
			<b>Full Address</b>
			<span id="requiredRegisterFullAddress" class="tooltip">Required</span><br>
			<textarea class="formValidation" name="fullAddress" oninput="inputValid('fullAddress', 'requiredRegisterFullAddress')"></textarea>
			<br>
			
			<b>Postal Code</b>
			<span id="requiredRegisterPostalCode" class="tooltip">Required</span><br>
			<input class="formValidation" type="text" name="postalCode" oninput="inputValid('postalCode', 'requiredRegisterPostalCode')">
			<br>
			<b>Phone Number</b>
			<span id="requiredRegisterPhoneNumber" class="tooltip">Required</span>
			<span id="requiredRegisterPhoneNumberOnly" class="tooltip">Number only</span><br>
			<input class="formValidation" type="text" name="phoneNumber" onchange="inputNumberValid('phoneNumber', 'requiredRegisterPhoneNumberOnly')" oninput="inputValid('phoneNumber', 'requiredRegisterPhoneNumber')">
			<br>
			<br>
			<br>
			<input type="submit" value="Register" onclick="validationRegisterButton(event)">
		</form>
		<br><br>
		<p><b>Already registered? Login <a class="btnHere" href="/JSP/Login">here</a></b></p>
	</div>
	

</body>
</html>
