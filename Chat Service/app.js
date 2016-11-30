var express = require('express')
var app = express()
// var FCM = require('fcm').FCM
var FCM = require('fcm-push')
var bodyParser = require('body-parser')
app.use( bodyParser.json() )       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
}))

var serverKey = "AIzaSyA4ChAd4LQ_oM-9de2iBv7fS3a2ZbzbI0I"
var fcm = new FCM(serverKey)
var userToken = {}


app.post('/registerToken', function (req, res) {
	var username = req.body.username
    var token = req.body.token
    userToken[username] = token
	res.send("1")
})

app.post('/sendMessage', function (req, res) {
	var usernamePengirim = req.body.usernamePengirim
	var usernamePenerima = req.body.usernamePenerima
    var pesan = req.body.pesan
    var token = userToken[usernamePenerima]
    var message = {
	    to: token,
	    notification: {
	        title: usernamePengirim, 
	        body: pesan 
	    }
	};

    fcm.send(message, function(err, response){
	    if (err) {
	        res.send(err)
	    } else {
	        res.send("1")
	    }
	});
})

app.listen(3000, function () {
	console.log('Example app listening on port 3000!')
})
