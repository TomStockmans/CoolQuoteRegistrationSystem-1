import {bindable, inject} from "aurelia-framework";
import {Line} from "../conversation";
import {Hotkeys} from "../../hotkeys";
import {Logger} from "../../util/cqrs-logging";
import {ValidationRules, ValidationControllerFactory, validateTrigger} from "aurelia-validation";

@inject(Hotkeys, ValidationControllerFactory)
class EditableConversation {
  @bindable conversation;
  @bindable save;
  hotkeys;
  validation;
  lines;
  editingLine;

  constructor(Hotkeys, ValidationControllerFactory) {
    this.hotkeys = Hotkeys;
    this.validation = ValidationControllerFactory.createForCurrentScope();
    this.init();
    ValidationRules
      .ensure(l => l.author).required()
      .ensure(l => l.text).required()
      .on(EditableLine);
  }

  init() {
    this.lines = [];
    this.focusNextLine();
  }

  doAfterValidation(success) {
    this.validation.validate()
      .then(errors => {
        this.validationErrors = errors;
        if (this.validationErrors.length == 0) {
          success();
        }
      })
      .catch(err => Logger.error('something terrible has happened', err));
  }

  addSpeechLine() {
    this.conversation.addLine(new Line("SPEECH", this.editingLine.text, this.editingLine.author, false));
    this.focusNextLine();
  }

  focusNextLine() {
    this.editingLine = new EditableLine();
    this.lines.push(this.editingLine);
  }

  /** key event handlers **/
  next(event) {
    if (this.hotkeys.submitQuoteKeyPressed(event)) {
      this.doAfterValidation(() => {
        this.addSpeechLine();
        this.save();
        this.init();
      });
      return false;
    }
    if (this.hotkeys.nextLineKeyPressed(event)) {
      this.doAfterValidation(() => {
        this.addSpeechLine();
      });
      return false;
    }
    if (this.validationErrors && this.validationErrors.length) {
      this.validation.reset();
      this.validationErrors = [];
    }
    return true;
  }

  blockEnter(event) {
    return event.key !== 'Enter';
  }
}

class EditableLine {
  author = '';
  text = '';
  hasFocus = true;
}

export { EditableConversation }
