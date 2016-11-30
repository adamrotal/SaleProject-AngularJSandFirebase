importScripts('https://www.gstatic.com/firebasejs/3.5.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/3.5.2/firebase-messaging.js');

var config = {
    apiKey: "AIzaSyBCpjAiiNSybRgyAf40gtsYHINzsoUG2bY",
    authDomain: "chatonlineshop-ad9fd.firebaseapp.com",
    databaseURL: "https://chatonlineshop-ad9fd.firebaseio.com",
    storageBucket: "chatonlineshop-ad9fd.appspot.com",
    messagingSenderId: "1052173421854"

};
  
firebase.initializeApp(config);

const messaging = firebase.messaging();

