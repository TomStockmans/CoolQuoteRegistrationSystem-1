import {bindable} from "aurelia-framework";

export class Participants {
  
  @bindable participants = null;

  participants() {
    if (!this.participants) {
      return "Nobody";
    }
    return this.participants.map(p => p.name).join(", ");
  }
}
