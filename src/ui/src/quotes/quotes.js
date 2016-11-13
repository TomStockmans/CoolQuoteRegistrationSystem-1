import {inject} from "aurelia-framework";
import {HttpClient, json} from "aurelia-fetch-client";
import {Logger} from "../util/cqrs-logging";

@inject(HttpClient)
export class Quotes {

  constructor(fetchClient) {
    this.http = fetchClient;
  }

  all() {
    return this.http.fetch("conversation").then(resp => resp.json());
  }

  save(quote) {
    Logger.debug('Saving new Quote', quote);
    return this.http
      .fetch("conversation", {
        method: "post",
        body: quote.json()
      })
      .then(resp => resp.json())
      .then(json => {
        Logger.debug(`Saved with id: ${json.id}`, json);
        return json;
      });
  }
}
