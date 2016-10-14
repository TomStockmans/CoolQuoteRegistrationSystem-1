import {inject} from "aurelia-framework";
import {Quotes} from "../quotes";
import {Quote} from "../quote";

@inject(Quotes)
export class Add {
  
  constructor(Quotes) {
    this.Quotes = Quotes;
  }
  
  add() {
    let quote = new Quote(this.participant, this.content);
    // let participants = this.participants.split(' ').map(p => {return {name:p, victim:false}});
    // let text = this.content;
    this.Quotes.save(quote);
  }
}
