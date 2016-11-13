import {inject} from "aurelia-framework";
import {Quotes} from "../quotes";

@inject(Quotes)
export class ListIndex {

  constructor(Quotes){
    this.Quotes = Quotes;
  }

  activate() {
    this.Quotes.all()
      .then(quotes => this.quotes = quotes)
      .catch(err => this.err = err);
  }

}
