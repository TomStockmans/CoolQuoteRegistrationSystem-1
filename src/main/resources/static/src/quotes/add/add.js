import {inject} from "aurelia-framework";
import {Conversation, Line} from "../conversation";
import {Quotes} from "../quotes";

@inject(Quotes)
export class Add {

  constructor(Quotes) {
    this.Quotes = Quotes;
    let self = this;
    this.conversation = new Conversation();
    this.speech = {};
    this.context = {};
    this.fillInSpeech = true;
    this.focusSpeech = true;
    this.focusContext = false;
    this.myKeypressCallback = this.keypressInput.bind(self);
  }

  activate() {
    window.addEventListener('keypress', this.myKeypressCallback, false);
  }

  deactivate() {
    window.removeEventListener('keypress', this.myKeypressCallback);
  }

  // This function is called by the aliased method
  keypressInput(e) {
    if (e.code === 'KeyB' && e.ctrlKey) {
      this.fillInSpeech = !this.fillInSpeech;
      if (this.fillInSpeech) {
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

  addQuote() {
    this.Quotes.save(this.conversation)
      .then(savedConvo => {
        this.conversation = new Conversation();
      });
  }
}
