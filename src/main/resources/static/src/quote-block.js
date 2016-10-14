import {WebAPI} from './web-api';
import {Conversation} from './conversation';

export class QuoteBlock {
    addQoute() {
      let conversation = new Conversation();
      
      this.content.split('\n').forEach(line => conversation.addLine(line));
      // COMMENT: dit kan je ook doen door conversation.addLine rechtstreeks door te geven, maar dan moet je this binden
      // this.content.split('\n').forEach(conversation.addLine.bind(conversation));
      
      new WebAPI().saveQuote(conversation);
    }
  
}
