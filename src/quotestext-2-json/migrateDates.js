db.conversation.find().forEach(function(c) {
    c.createdOn = new Date();
    db.conversation.save(c);
});