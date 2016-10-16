import {json} from "aurelia-fetch-client";

class Conversation {
  constructor() {
    this.lines = [];
  }

  addLine(line) {
    this.lines.push(line);
  }

  json() {
    return json(this);
  }
}

class Line {
  constructor(lineType, text, author, punchLine) {
    this.lineType = lineType;
    this.text = text ? text.trim() : '';
    this.participants = author ? [new Participant(author)] : [];
    this.punchLine = punchLine;
  }
}

class Participant {
  constructor(name) {
    this.name = name ? name.trim() : '';
  }
}

export {Conversation, Line, Participant};
