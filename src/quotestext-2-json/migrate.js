db.conversation.insert([
{"lines":[{"punchLine":false,"participants":[{"victim":false,"name":"Lion-O"}],"text":" SNARF! Get off that goddamn desktop!","lineType":"SPEECH"},{"punchLine":false,"text":"Snarf looks at Lion-O for a while","lineType":"CONTEXT"},{"punchLine":false,"participants":[{"victim":false,"name":"Snarf"}],"text":" snaaaarff snaaarffff!","lineType":"SPEECH"}],"_class":"be.swsb.cqrs.conversation.Conversation","createdOn":"2017-01-25T22:50:23.072Z"},{"lines":[{"punchLine":false,"participants":[{"victim":false,"name":"Snarf"}],"text":" Snarf snarf snarf?","lineType":"SPEECH"},{"punchLine":false,"participants":[{"victim":false,"name":"Lion-O"}],"text":" ohhh shut the fuck up snarf","lineType":"SPEECH"}],"_class":"be.swsb.cqrs.conversation.Conversation","createdOn":"2017-01-25T22:50:23.073Z"}
]);
        db.conversation.updateMany(
            {},
            { $set: {"createdOn": new Date()} }
        );
        