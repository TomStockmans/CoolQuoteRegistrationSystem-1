import {WebAPI} from './web-api';

export class App {
    addQoute() {
      new WebAPI().saveQuote(this.content);
    }
  
}
