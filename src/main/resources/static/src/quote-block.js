import {WebAPI} from './web-api';
import {Conversation} from './conversation';

export class QuoteBlock {
    addQoute() {
      let conversation = new Conversation();
      this.content.split('\n').forEach(conversation.addLine.bind(conversation));
      new WebAPI().saveQuote(conversation);
    }
  
}
