var express = require('express')
var cors = require('cors')
var app = express()
// var FCM = require('fcm').FCM
var FCM = require('fcm-push')
var bodyParser = require('body-parser')
app.use(cors())
app.use( bodyParser.json() )       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
}))

var serverKey = "AIzaSyA4ChAd4LQ_oM-9de2iBv7fS3a2ZbzbI0I"
var fcm = new FCM(serverKey)
var userToken = {}


app.get('/registerToken', function (req, res, next) {
	var username = req.query.username
    var token = req.query.token
    userToken[username] = token
    console.log(username)
    console.log(token)
	res.send("1")
})

app.get('/sendMessage', function (req, res, next) {
	var usernamePengirim = req.query.usernamePengirim
	var usernamePenerima = req.query.usernamePenerima
    var pesan = req.query.pesan
    console.log(usernamePengirim)
    console.log(usernamePenerima)
    console.log(pesan)
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
