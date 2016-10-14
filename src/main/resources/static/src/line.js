import {Participant} from './participant';

export class Line {
  constructor(lineType, text, author) {
    this.lineType = lineType;
    this.text = text ? text.trim() : '';
    this.participants = [];
    if (author) {
      this.participants.push(new Participant(author.trim()));
    } 
  }
}
