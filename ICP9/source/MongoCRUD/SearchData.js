

var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://root:12345@ds115569.mlab.com:15569/icp9';
var findUser = function(db, callback) {
    var cursor =db.collection('username').find( );
    cursor.each(function(err, doc) {
        assert.equal(err, null);
        if (doc != null) {
            console.log(doc);
        } else {
            callback();
        }
    });
};
var findUserwithName = function(db,callback) {
    var cursor = db.collection('username').find({"userId":"pola1996"});
    cursor.each(function(err,doc) {
        assert.equal(err,null);
        if(doc != null)
        {
            console.log("First Name:" + doc.name.first);
            console.log("Last Name:" + doc.name.last);
            console.log("city:" + doc.city);
        }
    });
}
var findUserwithUniversity = function(db, callback) {
    var cursor = db.collection('username').find({"mobileNumber":"+18162172712"});
    cursor.each(function(err,doc){
       assert.equal(err,null);
       if(doc != null)
       {
           console.log("First Name:" + doc.name.first);
           console.log("last name:" + doc.name.last);
           console.log("mail-id:" + doc.mailId);
           console.log("city:" + doc.city);

       }
    });
}
MongoClient.connect(url, function(err, db) {
    assert.equal(null, err);
    findUserwithUniversity(db, function() {
        db.close();
    });
});