var express = require('express');
var app = express();

const { MongoClient } = require("mongodb");
const uri = "mongodb://localhost:27017";
const client = new MongoClient(uri);

app.use(express.json());

app.get('/', (req,res) => {
  res.send("Hello World");
});

app.get('/ipAddress', (req,res) => {
  res.send("Hello World");
});

app.get('/localTime', (req,res) => {
  res.send("Hello World");
});

app.get('/name', (req,res) => {
  res.send("Tyson Brown");
});

var server = app.listen(8081, (req,res) => {
  var host = server.address().address;
  var port = server.address().port;
  console.log("Serving is running on http://%s:%s", host, port);
})

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