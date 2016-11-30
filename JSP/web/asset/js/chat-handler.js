function registerAJAX(newUsername, newToke) {
    var http = new XMLHttpRequest();
    var url = "http://localhost:3000/registerToken";
    var params = "token="+newToke+"&username="+newUsername;
	
    http.open("GET", url+"?"+params, true);

    //Send the proper header information along with the request
    //http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    //http.setRequestHeader("Content-length", params.length);
    //http.setRequestHeader("Connection", "close");

    http.onreadystatechange = function() {//Call a function
        if(http.readyState == 4 && http.status == 200) {
          console.log(this.responseText);
        }
    }

    http.send(null);
}

var config = {
    apiKey: "AIzaSyBCpjAiiNSybRgyAf40gtsYHINzsoUG2bY",
    authDomain: "chatonlineshop-ad9fd.firebaseapp.com",
    databaseURL: "https://chatonlineshop-ad9fd.firebaseio.com",
    storageBucket: "chatonlineshop-ad9fd.appspot.com",
    messagingSenderId: "1052173421854"

};
  
firebase.initializeApp(config);

const messaging = firebase.messaging();
messaging.requestPermission()
.then(function() {
	console.log('Notification permission granted.');
	return messaging.getToken();
})
.then(function(token) {
    var selfUsername = $("#usernameData").html();
    console.log(selfUsername);
    console.log(token);
    registerAJAX(selfUsername,token);
})
.catch(function(err) {
    console.log('Unable to get permission to notify.', err);
});

messaging.onMessage(function(payload) {
    var newHTML = $("#chatData").html()+'<p class="chat friend" >' + payload.notification.body + '</p><br>';
    $("#chatData").html(newHTML);
    console.log('onMessage : ',payload.notification.body);
});