import{HttpRequest} from './httprequest';

export class ConversationsRequester {
    constructor() {
        this.httpRequest = new HttpRequest();
    }

    getAllConversations() {
        return this.httpRequest.get("conversation");
    }

    getConversation(id) {
        return this.httpRequest.getWithParams("conversation", id);
    }

    findConversation(participant) {
        var querystring = "participant=" + participant;
        return this.httpRequest.getWithQueryParams("conversation/find", querystring);
    }

    postConversation(conversation) {
        this.httpRequest.post("conversation", conversation);
    }

    deleteConversation(id) {
        this.httpRequest.delete("conversation", id);
    }

    updateConversation(id, conversation) {
        this.httpRequest.put("conversation", id, conversation);
    }
}