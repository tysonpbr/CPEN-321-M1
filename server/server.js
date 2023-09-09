var express = require('express');
var app = express();

const { MongoClient } = require("mongodb");
const uri = "mongodb://127.0.0.1:27017";
const client = new MongoClient(uri);

app.use(express.json());

app.get('/', (req,res) => {
  res.send("Hello World");
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