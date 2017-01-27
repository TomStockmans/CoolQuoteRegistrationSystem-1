import * as fs from 'fs';
import * as process from 'process';
import * as readline from 'readline';

class QuoteBuilder {
    private quote: Quote = null;
    private hasAtLeastOneQuote: boolean = false;

    parseLine(line: string) {
        this.quote
            ? this.quote.addLine(new Line(line))
            : this.quote = new Quote(new Line(line));
    }

    writeQuote(target) {
        if (this.quote != null) target.write(this.prependQuoteIfAtLeastOneQuoteWasWritten());
        this.hasAtLeastOneQuote = true;
        this.quote = null;
    }

    private prependQuoteIfAtLeastOneQuoteWasWritten() {
        return this.hasAtLeastOneQuote
            ? ',' + JSON.stringify(this.quote)
            : JSON.stringify(this.quote);
    }
}

class Quote {
    private lines: Array<Line> = [];
    private createdOn:Date;
    private _class = "be.swsb.cqrs.conversation.Conversation";

    constructor(line: Line) {
        this.addLine(line);
        this.createdOn = new Date();
    }

    addLine(line: Line): void {
        this.lines.push(line);
    }
}

class Line {
    private text: string;
    private participants: Participant[];
    private lineType:string; // 'CONTEXT' | 'SPEECH'
    private punchLine: boolean = false;

    constructor(line: string) {
        this.parse(line);
    }

    parse(line: string) {
        let split = line.split(':');
        if (split.length > 1) {
            this.participants = [new Participant(split[0])];
            this.text = split[1];
            this.lineType = 'SPEECH';
        } else {
            this.text = line;
            this.lineType = 'CONTEXT';
        }
    }
}

class Participant {
    private name: string;
    private victim: boolean = false;

    constructor(name: string) {
        this.name = name;
    }
}


!process.argv[2] && !process.argv[3]
    ? console.log(`You'll have to supply a filename as a second argument. e.g.:\n
                   node converter examplequotes.txt`)
    : convert(process.argv[2], process.argv[3]);

export function convert(sourceFile: string, targetFile: string) {
    if (!targetFile) {
        console.log(`No targetFile was given, using default`);
        targetFile = 'migrate.js';
    }
    console.log(`Converting [${sourceFile}] to [${targetFile}]`);
    let target = fs.createWriteStream(targetFile);

    let quoteBuilder = new QuoteBuilder();

    const rl = readline.createInterface({input: fs.createReadStream(sourceFile)});

    let startMigrate = function () {
        target.write('db.conversation.insert([\n');
    };

    function updateTimestamps() {
        target.write(`
        db.conversation.updateMany(
            {},
            { $currentDate: { createdOn: true } }
        );
        `);
        //actually replace after all scripts are done with:
        target.write(`
        db.conversation.find().forEach(function(c) {
            c.createdOn = new Date();
            db.conversation.save(c);
        });
        `);
    }

    let endMigrate = function () {
        target.write('\n]);');
        // updateTimestamps();
    };

    startMigrate();
    rl.on('line', (line) => {
        !line
            ? quoteBuilder.writeQuote(target)
            : quoteBuilder.parseLine(line);
    });
    rl.on('close', () => {
        quoteBuilder.writeQuote(target);
        endMigrate();
    });
}