import {WebAPI} from './web-api';
import {Conversation} from './conversation';
import {Line} from './line';
import {ConversationsRequester} from './conversationsRequester';


function parseRawLine(rawLine) {
  let type = parseType(rawLine);
  let text = parseText(rawLine);
  let punchLine = parsePunchLine(rawLine);
  let author = parseAuthor(rawLine);

  return new Line(type, text, author, punchLine);
}

function parseType(rawLine) {
  if (rawLine.startsWith('/c') || rawLine.startsWith('/p/c')) {
    return 'CONTEXT';
  }
  return 'SPEECH';
}

function removeFlags(rawLine) {
  return rawLine.replace('/c', '').replace('/p', '');
}

function parseText(rawLine) {
  return removeFlags(rawLine).replace(/.*:/, '');
}

function parsePunchLine(rawLine) {
  if (rawLine.startsWith('/p') || rawLine.startsWith('/c/p')) {
    return true;
  }
  return false;
}

function parseAuthor(rawLine) {
  if (rawLine.indexOf(':') === -1) {
    return '';
  }

  return removeFlags(rawLine).replace(/:.*/, '');
}

export class QuoteBlock {
    addQoute() {
      let conversation = new Conversation();
      
      this.content.split('\n')
        .map(parseRawLine)
        .forEach(line => conversation.addLine(line));
      new ConversationsRequester().postConversation(conversation);
    }

    liveUpdate() {
      let conversation = new Conversation();
      this.content.split('\n')
        .map(parseRawLine)
        .forEach(line => conversation.addLine(line));

      this.live = conversation;
    }
}
