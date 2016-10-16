import {inject} from "aurelia-framework";
import {Quotes} from "../quotes";
import {Conversation, Line} from "../conversation";
import {Logger} from "../../util/cqrs-logging";

@inject(Quotes)
export class ContinuousAdd {

  constructor(Quotes) {
    this.Quotes = Quotes;
    this.conversation = new Conversation();
  }

  save() {
    this.Quotes.save(this.conversation);
    this.conversation = new Conversation();
  }
}
