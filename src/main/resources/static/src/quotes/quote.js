import {bindable} from "aurelia-framework";

export class Quote {

  @bindable quote = null;

  bind() {
    this.lines = this.quote.lines;
  }

  unbind() {
    this.lines = [];
  }
}
