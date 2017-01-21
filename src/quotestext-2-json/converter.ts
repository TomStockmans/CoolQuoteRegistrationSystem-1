import * as fs from 'fs';
import * as process from 'process';

!process.argv[2] && !process.argv[3]
    ? console.log(`You'll have to supply a filename as a second argument. e.g.:\nnode converter examplequotes.txt`)
    : convert(process.argv[2], process.argv[3]);

export function convert(sourceFile: string, targetFile: string) {
    if (!targetFile) {
        console.log(`No targetFile was given, using default`);
        targetFile = 'output.txt';
    }
    console.log(`Converting ${sourceFile} to ${targetFile}`);
    fs.readFile(sourceFile, (err, data) => {
        fs.writeFile(targetFile, data, (err) => err ? console.log(err) : true);
    });
    // fs.createReadStream(sourceFile).pipe(fs.createWriteStream('snarfsnarf.txt'));

}

