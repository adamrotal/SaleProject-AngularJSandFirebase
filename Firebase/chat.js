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
	console.log(token);
})
.catch(function(err) {
	console.log('Unable to get permission to notify.', err);
});

messaging.onMessage(function(payload) {
	console.log('onMessage : ',payload);
});