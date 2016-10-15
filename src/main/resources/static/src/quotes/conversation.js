import {computedFrom} from "aurelia-framework";
import {json} from "aurelia-fetch-client";
import {Logger} from "../util/cqrs-logging";

class Conversation {

  lines = [];
  constructor() {
  }

  addLine(line) {
    this.lines.push(line);
  }

  // @computedFrom("lines")
  // get
  hasLines() {
    Logger.debug(`this.lines: ${this.lines}`);
    Logger.debug(`[] == true: ${[] == true}`);
    Logger.debug(`this.lines == true: ${this.lines == true}`);
    return this.lines && this.lines.length > 0;
  }

  json() {
    return json(this);
  }
}

class Line {
  constructor(lineType, text, author, punchLine) {
    this.lineType = lineType;
    this.text = text ? text.trim() : '';
    this.participants = [];
    this.punchLine = punchLine;
    if (author) {
      this.participants.push(new Participant(author.trim()));
    }
  }
}

class Participant {
  constructor(name) {
    this.name = name;
  }
}

export { Conversation, Line, Participant };
