import {bindable} from "aurelia-framework";

export class Quote {

  @bindable quote = null;

  lines() {
    let allLines = this.quote.lines;
    allLines.push(this.quote.punchLine);
    return allLines;
  }

}
