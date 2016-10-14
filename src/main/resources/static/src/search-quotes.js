import { ConversationsRequester } from './conversationsRequester';

export class SearchQuotes {
  message = "Hier kan je zoeken naar een bepaalde quote";

  constructor() {
    this.participant = undefined;
    this.victim = undefined;
    this.response = {};
  }

  find() {
    this.response = {};
    if (this.participant || this.victim) {
      this.response = new ConversationsRequester().findConversation(this.participant, this.victim);
    }
  }
}