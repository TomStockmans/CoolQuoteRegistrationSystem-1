"use strict";
var fs = require("fs");
var process = require("process");
!process.argv[2] && !process.argv[3]
    ? console.log("You'll have to supply a filename as a second argument. e.g.:\nnode converter examplequotes.txt")
    : convert(process.argv[2], process.argv[3]);
function convert(sourceFile, targetFile) {
    if (!targetFile) {
        console.log("No targetFile was given, using default");
        targetFile = 'output.txt';
    }
    console.log("Converting " + sourceFile + " to " + targetFile);
    fs.readFile(sourceFile, function (err, data) {
        fs.writeFile(targetFile, data, function (err) { return err ? console.log(err) : true; });
    });
    // fs.createReadStream(sourceFile).pipe(fs.createWriteStream('snarfsnarf.txt'));
}
exports.convert = convert;
