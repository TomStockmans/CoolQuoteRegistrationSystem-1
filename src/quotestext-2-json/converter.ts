import * as fs from 'fs';
import * as process from 'process';
import * as readline from 'readline';

!process.argv[2] && !process.argv[3]
    ? console.log(`You'll have to supply a filename as a second argument. e.g.:\nnode converter examplequotes.txt`)
    : convert(process.argv[2], process.argv[3]);

export function convert(sourceFile: string, targetFile: string) {
    if (!targetFile) {
        console.log(`No targetFile was given, using default`);
        targetFile = 'output.json';
    }
    console.log(`Converting [${sourceFile}] to [${targetFile}]`);
    let target = fs.createWriteStream(targetFile);

    readline.createInterface({
        input: fs.createReadStream(sourceFile)
    })
    .on('line', (line) => {
        target.write(`${line}\n`);
    });
}