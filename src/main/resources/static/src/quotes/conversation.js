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
