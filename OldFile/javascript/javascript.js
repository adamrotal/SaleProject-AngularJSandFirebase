function countPrice() {
	var price = document.getElementById('initialPrice').value;
	var nBuy = document.getElementById('quantity').value;
	
	if (nBuy != "") {
		if (parseInt(nBuy) > 0) {
			var total = parseInt(nBuy * price);
			document.getElementById('totalPrice').innerHTML = total.toLocaleString();
		} else {
			return false;
		}
	} else {
		var newPrice = parseInt(price);
		document.getElementById('totalPrice').innerHTML = newPrice.toLocaleString();
	}
}

function validationPurchase(event) {
	event.preventDefault();
	var modal = document.getElementById('myModal');
	//var btn = document.getElementById("btnConfirm");
	var spanYes = document.getElementsByClassName("btnYesModal")[0];
	var spanNo = document.getElementsByClassName("btnNoModal")[0];

    modal.style.display = "block";

	spanNo.onclick = function() {
	    modal.style.display = "none";
	}

	spanYes.onclick = function() {
		document.getElementById("myFormConfirmation_Purchase").submit();
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
}

function validationCreditCard() {
	var reg = /^[0-9]{12}$/;
	var id = document.getElementById('credit').value;
  	var checking = reg.test(id); 

	if (checking || id == "") {
		var tooltip = document.getElementById('requiredConfirmPurchaseCreditCard12');
		tooltip.className = "tooltip";
	} else {		
		var tooltip = document.getElementById('requiredConfirmPurchaseCreditCard12');
		tooltip.className += " visibleTooltip";
	}
}

function validationVerification() {
	var reg = /^[0-9]{3}$/;
	var id = document.getElementById('verification').value;
  	var checking = reg.test(id); 
	
	if (checking || id == "") {
		var tooltip = document.getElementById('requiredConfirmPurchaseDigitCard3');
		tooltip.className = "tooltip";
	} else {		
		var tooltip = document.getElementById('requiredConfirmPurchaseDigitCard3');
		tooltip.className += " visibleTooltip";
	}
}

function validationLoginButton(event) {
	event.preventDefault();
	var emailUsername = document.loginForm.email;
	var pass = document.loginForm.password;
	
	if (emailUsername.value == "") {
		var tooltip = document.getElementById('requiredLoginEmail');
		tooltip.className += " visibleTooltip";
	}

	if (pass.value == "") {
		var tooltip = document.getElementById('requiredLoginPassword');
		tooltip.className += " visibleTooltip";
	}

	if (emailUsername.value != "" && pass.value != "") {
		document.getElementById("myFormLogin").submit();
	}
}

function validationRegisterButton(event) {
	event.preventDefault();
	var fullName = document.registerForm.fullName;
	var username = document.registerForm.username;
	var email = document.registerForm.email;
	var pass = document.registerForm.password;
	var confirmPass = document.registerForm.confirmpassword;
	var fullAddress = document.registerForm.fullAddress;
	var postalCode = document.registerForm.postalCode;
	var phoneNumber = document.registerForm.phoneNumber;
	var isComplete = true;


	if (fullName.value == "") {
		var tooltip = document.getElementById('requiredRegisterFullName');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (username.value == "") {
		var tooltip = document.getElementById('requiredRegisterUsername');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (email.value == "") {
		var tooltip = document.getElementById('requiredRegisterEmail');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (pass.value == "") {
		var tooltip = document.getElementById('requiredRegisterPassword');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (confirmPass.value == "") {
		var tooltip = document.getElementById('requiredRegisterConfirmPass');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (fullAddress.value == "") {
		var tooltip = document.getElementById('requiredRegisterFullAddress');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (postalCode.value == "") {
		var tooltip = document.getElementById('requiredRegisterPostalCode');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (phoneNumber.value == "") {
		var tooltip = document.getElementById('requiredRegisterPhoneNumber');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}


	if (isComplete) {
		document.getElementById("myFormRegister").submit();
	}	
}

function validationAddProductButton(event) {
	event.preventDefault();
	var name = document.addProductForm.name;
	var description = document.addProductForm.description;
	var price = document.addProductForm.price;
	var photo = document.addProductForm.photo;
	var isComplete = true;
	
	if (name.value == "") {
		var tooltip = document.getElementById('requiredAddProductName');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (description.value == "") {
		var tooltip = document.getElementById('requiredAddProductDescription');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (price.value == "") {
		var tooltip = document.getElementById('requiredAddProductPrice');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (photo.value == "") {
		var tooltip = document.getElementById('requiredAddProductPhoto');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (isComplete) {
		//console.log("complitos")
		document.getElementById("myFormAddProduct").submit();
	}
}

function validationConfirmPurchaseButton(event) {
	event.preventDefault();
	var consignee = document.getElementById('consignee');
	var fullAddress = document.getElementById('address');
	var postalCode = document.getElementById('postal');
	var phoneNumber = document.getElementById('phone');
	var creditCard = document.getElementById('credit');
	var codeVerification = document.getElementById('verification');
	var isComplete = true;
	
	if (consignee.value == "") {
		var tooltip = document.getElementById('requiredConfirmPurchaseConsignee');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (fullAddress.value == "") {
		var tooltip = document.getElementById('requiredConfirmPurchaseFullAddress');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (postalCode.value == "") {
		var tooltip = document.getElementById('requiredConfirmPurchasePostalCode');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (phoneNumber.value == "") {
		var tooltip = document.getElementById('requiredConfirmPurchasePhoneNumber');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (creditCard.value == "") {
		var tooltip = document.getElementById('requiredConfirmPurchaseCreditCard');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (codeVerification.value == "") {
		var tooltip = document.getElementById('requiredConfirmPurchaseDigitCard');
		tooltip.className += " visibleTooltip";
		isComplete = false;
	}

	if (isComplete) {
		validationPurchase(event);
	}
}


function inputValid(name, tooltipID) {
	var input = document.getElementsByName(name)[0];
	
	if (input.value == "") {
		var tooltip = document.getElementById(tooltipID);
		tooltip.className += " visibleTooltip";
	} else {
		var tooltip = document.getElementById(tooltipID);
		tooltip.className = "tooltip";
	}
}

function inputNumberValid(name, tooltipID) {
	var number = document.getElementsByName(name)[0];
	var reg = new RegExp('^\\d+$');
	var checkingNumber = reg.test(number.value);
	//console.log(number.value);

	if (checkingNumber || number.value == "") {
		var tooltip = document.getElementById(tooltipID);
		tooltip.className = "tooltip";
		//console.log("number only");
	} else {		
		var tooltip = document.getElementById(tooltipID);
		tooltip.className += " visibleTooltip";
		//console.log("not number only")
	}
}

function inputEmailValid(nama, tooltipID) {
    var email = document.getElementsByName(nama)[0];
    var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var checkingFormat = reg.test(email.value);
	//console.log(number.value);

	if (checkingFormat || email.value == "") {
		var tooltip = document.getElementById(tooltipID);
		tooltip.className = "tooltip";
		console.log("email valid");
	} else {		
		var tooltip = document.getElementById(tooltipID);
		tooltip.className += " visibleTooltip";
		console.log("email not valid")
	}
}