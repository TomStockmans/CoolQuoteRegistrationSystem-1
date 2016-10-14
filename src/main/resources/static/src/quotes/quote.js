import {json} from "aurelia-fetch-client";

export class Quote {
  constructor(participant, text) {
    this.lines = [{
      lineType: 'SPEECH',
      participants: [{name:participant, victim:false}],
      text: text
    }];
    // this.conversationDate = new Date();
  }
  
  json() {
    return json(this);
  }
}
