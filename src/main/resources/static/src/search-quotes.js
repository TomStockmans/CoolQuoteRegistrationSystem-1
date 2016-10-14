import { ConversationsRequester } from './conversationsRequester';

export class SearchQuotes {
  message = "Hier kan je zoeken naar een bepaalde quote";

  constructor() {
    this.participant = undefined;
    this.response = {};
  }

  find() {
    this.response = {};
    if (!(this.participant == undefined || this.participant == "")) {
      this.response = new ConversationsRequester().findConversation(this.participant);
    }
  }
}