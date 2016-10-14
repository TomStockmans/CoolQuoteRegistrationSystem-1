import {WebAPI} from './web-api';
import {Conversation} from './conversation';
import {Line} from './line';
import {ConversationsRequester} from './conversationsRequester';


function parseRawLine(rawLine) {
  let parserFunction = getLineParserFunction(rawLine);
  return parserFunction(rawLine);
}

function getLineParserFunction(rawLine) {
  if (rawLine.startsWith('/c:')) {
    return contextLineParser;
  }
  return speechLineParser;
}

function contextLineParser(rawLine) {
  return new Line('CONTEXT', rawLine.substring(3));
}

function speechLineParser(rawLine) {
  let lineParts = rawLine.split(':',2);
  let author = lineParts[0];
  let content = lineParts[1];
  return new Line('SPEECH', content, author);
}

export class QuoteBlock {
    addQoute() {
      let conversation = new Conversation();
      
      this.content.split('\n')
        .map(parseRawLine)
        .forEach(line => conversation.addLine(line));
      new ConversationsRequester().postConversation(conversation);
    }
}
