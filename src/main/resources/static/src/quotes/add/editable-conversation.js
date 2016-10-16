import {bindable} from "aurelia-framework";
import {Logger} from "../../util/cqrs-logging";
import {Line} from "../conversation";

export class EditableConversation {
  @bindable conversation;
  @bindable save;

  constructor() {
    this.init();
  }

  init() {
    this.editingLine = {author:'author',text:'text',hasFocus:true};
    this.lines = [this.editingLine];
  }
  
  next(event) {
    Logger.debug(`next() executed - `, event);
    if (event.key === 'Enter') {
      this.conversation.addLine(new Line("SPEECH", this.editingLine.text, this.editingLine.author, false));
      this.focusNextLine();
    }
    if ((event.ctrlKey || event.metaKey) && event.key === 'Enter') {
      this.save();
      this.init();
    }
    return true;
  }

  focusNextLine() {
    this.editingLine.hasFocus = false;
    this.editingLine = {author:'author',text:'text',hasFocus:true};
    this.lines.push(this.editingLine);
  }

}
