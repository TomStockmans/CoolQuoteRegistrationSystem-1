import {bindable, inject} from "aurelia-framework";
import {Line} from "../conversation";
import {Hotkeys} from "../../hotkeys";
import {Logger} from "../../util/cqrs-logging";
import {ValidationRules, ValidationControllerFactory, validateTrigger} from "aurelia-validation";

@inject(Hotkeys, ValidationControllerFactory)
export class EditableConversation {
  @bindable conversation;
  @bindable save;
  hotkeys;
  validation;
  lines;
  editingLine;

  constructor(Hotkeys, ValidationControllerFactory) {
    this.hotkeys = Hotkeys;
    this.validation = ValidationControllerFactory.createForCurrentScope();
    // this.validation.validateTrigger = validateTrigger.manual;
    this.init();
    this.validation.addObject(
      this.editingLine,
      ValidationRules
      .ensure(l => l.author).required()
      .ensure(l => l.text).required()
      .rules);
  }

  init() {
    this.lines = [];
    this.focusNextLine();
  }
  addSpeechLine() {
    this.validation.validate();
    this.conversation.addLine(new Line("SPEECH", this.editingLine.text, this.editingLine.author, false));
  }

  focusNextLine() {
    this.editingLine = {author: '', text: '', hasFocus: true};
    this.lines.push(this.editingLine);
  }

  /** key event handlers **/
  next(event) {
    if (this.hotkeys.submitQuoteKeyPressed(event)) {
      this.addSpeechLine();
      this.save();
      this.init();
      return false;
    }
    if (this.hotkeys.nextLineKeyPressed(event)) {
      this.addSpeechLine();
      this.focusNextLine();
      return false;
    }
    return true;
  }

  blockEnter(event) {
    return event.key !== 'Enter';
  }
}
