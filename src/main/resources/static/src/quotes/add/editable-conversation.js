import {bindable, inject} from "aurelia-framework";
import {Line} from "../conversation";
import {Hotkeys} from "../../hotkeys";
import {Logger} from "../../util/cqrs-logging";

@inject(Hotkeys)
export class EditableConversation {
  @bindable conversation;
  @bindable save;

  constructor(Hotkeys) {
    this.Hotkeys = Hotkeys;
    this.init();
  }

  init() {
    this.lines = [];
    this.focusNextLine();
  }

  next(event) {
    if (this.Hotkeys.submitQuoteKeyPressed(event)) {
      this.addSpeechLine();
      this.save();
      this.init();
      return false;
    }
    if (this.Hotkeys.nextLineKeyPressed(event)) {
      this.addSpeechLine();
      this.focusNextLine();
      return false;
    }
    return true;
  }

  addSpeechLine() {
    this.conversation.addLine(new Line("SPEECH", this.editingLine.text, this.editingLine.author, false));
  }

  focusNextLine() {
    this.editingLine = {author: '', text: '', hasFocus: true};
    this.lines.push(this.editingLine);
  }

  blockEnter(event) {
    return event.key !== 'Enter';
  }
}
