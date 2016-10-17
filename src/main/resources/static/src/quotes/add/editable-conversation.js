import {bindable, inject} from "aurelia-framework";
import {Logger} from "../../util/cqrs-logging";
import {Line} from "../conversation";
import {Hotkeys} from "../../hotkeys";

@inject(Hotkeys)
export class EditableConversation {
  @bindable conversation;
  @bindable save;

  constructor(Hotkeys) {
    this.Hotkeys = Hotkeys;
    this.init();
  }

  init() {
    this.editingLine = {author:'author',text:'text',hasFocus:true};
    this.lines = [this.editingLine];
  }
  
  next(event) {
    Logger.debug(`next() executed - `, event);
    if (this.Hotkeys.submitQuoteKeyPressed(event)) {
      this.conversation.addLine(new Line("SPEECH", this.editingLine.text, this.editingLine.author, false));
      this.save();
      this.init();
      return false;
    }
    if (this.Hotkeys.nextLineKeyPressed(event)) {
      this.conversation.addLine(new Line("SPEECH", this.editingLine.text, this.editingLine.author, false));
      this.focusNextLine();
      return false;
    }
    return true;
  }

  focusNextLine() {
    this.editingLine.hasFocus = false;
    this.editingLine = {author:'author',text:'text',hasFocus:true};
    this.lines.push(this.editingLine);
  }

}
