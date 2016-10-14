import {Line} from './line';

export class Conversation {
    constructor() {
        this.lines = [];
    }

    addLine(line) {
        this.lines.push(line);
    }
}
