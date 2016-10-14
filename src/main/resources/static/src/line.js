import {Participant} from './participant';

export class Line {
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
