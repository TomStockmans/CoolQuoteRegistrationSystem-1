import {inject} from "aurelia-framework";
import {Quotes} from "../quotes";
import {Conversation} from "../conversation";
import {Notification} from 'aurelia-notification';
import {Logger} from "../../util/cqrs-logging";

@inject(Quotes, Notification)
export class ContinuousAdd {

  constructor(Quotes, Notification) {
    this.Quotes = Quotes;
    this.Notification = Notification;
    this.conversation = new Conversation();
  }

  save() {
    this.Quotes.save(this.conversation).then(() => {
      this.conversation = new Conversation();
      this.showQuoteSaved();
    });
  }

  showQuoteSaved() {
    this.Notification.success('Quote Saved');
  }
}
