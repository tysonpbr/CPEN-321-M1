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

app.post('/allBuildings', (req,res) => {

  const code = req.body.code.toUpperCase();

  const allBuildings = [
    { code: "ALRD", address: "1822 East Mall" },
    { code: "ANSO", address: "6303 North West Marine Drive" },
    { code: "AERL", address: "2202 Main Mall" },
    { code: "ACEN", address: "1871 West Mall" },
    { code: "AUDX", address: "1924 West Mall" },
    { code: "BINN", address: "6373 University Boulevard" },
    { code: "BIOL", address: "6270 University Boulevard" },
    { code: "BUCH", address: "1866 Main Mall" },
    { code: "BUTO", address: "1873 East Mall" },
    { code: "CCM", address: "4145 Wesbrook Mall" },
    { code: "CIRS", address: "2260 West Mall" },
    { code: "CHAN", address: "6265 Crescent Road" },
    { code: "GUNN", address: "2553 Wesbrook Mall" },
    { code: "CHBE", address: "2360 East Mall" },
    { code: "CHEM", address: "2036 Main Mall" },
    { code: "CEME", address: "6250 Applied Science Lane" },
    { code: "MINL", address: "2332 West Mall" },
    { code: "COPP", address: "2146 Health Sciences Mall" },
    { code: "DLAM", address: "2033 Main Mall" },
    { code: "DSOM", address: "6361 University Blvd" },
    { code: "KENN", address: "2136 West Mall" },
    { code: "EOS", address: "6339 Stores Road" },
    { code: "ESB", address: "2207 Main Mall" },
    { code: "ESC", address: "2335 Engineering Road" },
    { code: "FNH", address: "2205 East Mall" },
    { code: "FSC", address: "2424 Main Mall" },
    { code: "FORW", address: "6350 Stores Road" },
    { code: "LASR", address: "6333 Memorial Road" },
    { code: "FRWO", address: "6354 Crescent Road" },
    { code: "FRDM", address: "2177 Wesbrook Mall" },
    { code: "GEOG", address: "1984 West Mall" },
    { code: "CUNN", address: "2146 East Mall" },
    { code: "HEBB", address: "2045 East Mall" },
    { code: "HENN", address: "6224 Agricultural Road" },
    { code: "ANGU", address: "2053 Main Mall" },
    { code: "DMP", address: "6245 Agronomy Road" },
    { code: "IRSC", address: "1985 Learners' Walk" },
    { code: "ICCS", address: "2366 Main Mall" },
    { code: "IBLC", address: "1961 East Mall" },
    { code: "MCDN", address: "2199 West Mall" },
    { code: "SOWK", address: "2080 West Mall" },
    { code: "LAX", address: "2371 Main Mall" },
    { code: "LSK", address: "6356 Agricultural Road" },
    { code: "PARC", address: "6049 Nurseries Road" },
    { code: "LSC", address: "2350 Health Sciences Mall" },
    { code: "MCLD", address: "2356 Main Mall" },
    { code: "MCML", address: "2357 Main Mall" },
    { code: "MATH", address: "1984 Mathematics Road" },
    { code: "MATX", address: "1986 Mathematics Road" },
    { code: "MEDC", address: "2176 Health Sciences Mall" },
    { code: "MSL", address: "2185 East Mall" },
    { code: "MUSC", address: "6361 Memorial Road" },
    { code: "SCRF", address: "2125 Main Mall" },
    { code: "AUDI", address: "6344 Memorial Road" },
    { code: "IRC", address: "2194 Health Sciences Mall" },
    { code: "PHRM", address: "2405 Wesbrook Mall" },
    { code: "PONE", address: "2034 Lower Mall" },
    { code: "PONF", address: "2008 Lower Mall" },
    { code: "OSB2", address: "6108 Thunderbird Boulevard" },
    { code: "SRC", address: "6000 Student Union Blvd" },
    { code: "BRIM", address: "2355 East Mall" },
    { code: "UCEN", address: "6331 Crescent Road" },
    { code: "TFPB", address: "6358 University Blvd" },
    { code: "YURT", address: "3465 Ross Drive" },
    { code: "KPAV", address: "2211 Wesbrook Mall" },
    { code: "MGYM", address: "6081 University Blvd" },
    { code: "EDC", address: "2345 East Mall" },
    { code: "WESB", address: "6174 University Boulevard" },
    { code: "WMAX", address: "1933 West Mall" },
    { code: "SWNG", address: "2175 West Mall" },
  ]

  let address = "none";

  for (const building of allBuildings) {
    if (building.code == code) {
      address = building.address;
    }
  }

  res.send(address);
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