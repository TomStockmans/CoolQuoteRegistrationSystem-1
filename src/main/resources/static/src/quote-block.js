import {WebAPI} from './web-api';
import {Conversation} from './conversation';
import {Line} from './line';


function parseRawLine(rawLine) {
  let lineParts = rawLine.split(':',2);
  return new Line(lineParts[0], lineParts[1]);
}

export class QuoteBlock {
    addQoute() {
      let conversation = new Conversation();
      
      this.content.split('\n')
        .map(parseRawLine)
        .forEach(line => conversation.addLine(line));

      new WebAPI().saveQuote(conversation);
    }
}
