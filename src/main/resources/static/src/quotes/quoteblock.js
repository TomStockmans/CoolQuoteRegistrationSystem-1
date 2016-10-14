import {bindable} from "aurelia-framework";

export class Quoteblock {

  @bindable quote = null;

  bind() {
    this.lines = this.quote.lines;
  }

  unbind() {
    this.lines = [];
  }
}
