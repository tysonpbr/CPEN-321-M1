var express = require('express');
var app = express();

const { MongoClient } = require("mongodb");
const uri = "mongodb://localhost:27017";
const client = new MongoClient(uri);

app.use(express.json());

var server = app.listen(8081, (req,res) => {
  var host = server.address().address;
  var port = server.address().port;
  console.log("Serving is running on http://%s:%s", host, port);
})

app.get('/', (req,res) => {
  res.send("Hello World");
});

app.get('/ipAddress', (req,res) => {
  res.send(server.address().address);
});

app.get('/time', (req,res) => {
  var currentTime = new Date();

  var hours = currentTime.getHours();
  var minutes = currentTime.getMinutes();
  var seconds = currentTime.getSeconds();

  hours = (hours < 10 ? "0" : "") + hours;
  minutes = (minutes < 10 ? "0" : "") + minutes;
  seconds = (seconds < 10 ? "0" : "") + seconds;

  res.send(hours + ":" + minutes + ":" + seconds);
});

app.get('/name', (req,res) => {
  res.send("Tyson Brown");
});

// async function run() {
//   try {
//     await client.connect();
//     console.log("Mongo connected")
//     // var server = app.listen(8081, (req,res) => {
//     //   var host = server.address().address;
//     //   var port = server.address().port;
//     //   console.log("Serving is running on http://%s:%s", host, port);
//     // })
//   }
//   catch(e) {
//     console.log(e);
//     await client.close();
//   }
// }

// run();