import {WebAPI} from './web-api';

export class QuoteBlock {
    addQoute() {
      new WebAPI().saveQuote(this.content);
    }
  
}
