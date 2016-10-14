import {ConversationsRequester} from './conversationsRequester';

export class Quotes {

   constructor() {
       this.response =  new ConversationsRequester().getAllConversations();
    }
}
