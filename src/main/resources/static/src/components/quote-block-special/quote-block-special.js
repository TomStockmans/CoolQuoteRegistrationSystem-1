import {Conversation} from "../../conversation";
import {Line} from "../../line";
import {ConversationsRequester} from "../../conversationsRequester";

export class QuoteBlockSpecial {
    constructor() {
        this.conversation = new Conversation();
        this.speech = {};
        this.context = {};
        this.fillInSpeech = true;
        this.myKeypressCallback = this.keypressInput.bind(this);
    }

    activate() {
        window.addEventListener('keypress', this.myKeypressCallback, false);
    }

    deactivate() {
        window.removeEventListener('keypress', this.myKeypressCallback);
    }

    // This function is called by the aliased method
    keypressInput(e) {
        if(e.code === 'KeyB' && e.ctrlKey){
            this.fillInSpeech = !this.fillInSpeech;
            if(this.fillInSpeech){
                this.focusSpeech = true;
            } else {
                this.focusContext = true;
            }
        }
    }

    addSpeechLine() {
        this.conversation.addLine(new Line('SPEECH', this.speech.content, this.speech.author));
        this.speech.content = '';
    }

    addContextLine() {
        this.conversation.addLine(new Line('CONTEXT', this.context.content, this.context.author));
        this.context.content = '';
    }

    addQoute() {
        new ConversationsRequester().postConversation(this.conversation);
        this.conversation = new Conversation();
    }
}
