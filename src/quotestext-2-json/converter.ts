import * as fs from 'fs';
import * as process from 'process';
import * as readline from 'readline';




class QuoteBuilder {
    private quote: Quote;

    constructor() {
        this.quote = null;
    }

    parseLine(line: string) {
        this.quote
            ? this.quote.addLine(new Line(line))
            : this.quote = new Quote(new Line(line));
    }

    writeQuote(target) {
        target.write(JSON.stringify(this.quote));
        this.quote = null;
    }
}




class Line {
    private text: string;
    private author: string;

    constructor(line: string) {
        this.parse(line);
    }

    parse(line: string) {
        let split = line.split(':');
        if (split.length > 1) {
            this.author = split[0];
            this.text = split[1];
        } else {
            this.text = line;
        }
    }
}




class Quote {
    private lines: Array<Line> = [];

    constructor(line: Line) {
        this.addLine(line);
    }

    addLine(line: Line): void {
        this.lines.push(line);
    }
}

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

    let quoteBuilder = new QuoteBuilder();

    const rl = readline.createInterface({input: fs.createReadStream(sourceFile)});
    rl.on('line', (line) => {
        if (!line) {
            rl.emit('newline');
        }
        quoteBuilder.parseLine(line);
    });
    rl.on('newline', () => {
        quoteBuilder.writeQuote(target);
    });
}