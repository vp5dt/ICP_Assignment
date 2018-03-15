
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://root:12345@ds115569.mlab.com:15569/icp9';

var insertDocument = function(db, callback) {
    db.collection('username').insertOne( {
        "name" : {
          "first" : "bhavesh",
          "last" : "polareddy"
        },
        "userId" : "pola1996",
        "mailId" : "vp5dt@mail.umkc.edu",
        "mobileNumber" : "+18162172712",
        "city" : "kansas city"
    }, function(err, result) {
        assert.equal(err, null);
        console.log("Inserted a document into the username collection.");
        callback();
    });
};
MongoClient.connect(url, function(err, db) {
    assert.equal(null, err);
    insertDocument(db, function() {
        db.close();
    });
});