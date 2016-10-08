import {inject} from "aurelia-framework";
import {HttpClient, json} from "aurelia-fetch-client";

@inject(HttpClient)
export class Quotes {

  constructor(fetchClient) {
    this.http = fetchClient;
  }

  all() {
    return this.http.fetch("conversation.json").then(resp => resp.json());
  }
}
