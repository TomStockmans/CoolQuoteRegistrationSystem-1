import {bindable} from "aurelia-framework";

export class Quote {

  @bindable quote = null;

  bind() {
    let allLines = this.quote.lines;
    allLines.push(this.quote.punchLine);
    this.lines = allLines;
  }

  unbind() {
    this.lines = [];
  }
}
