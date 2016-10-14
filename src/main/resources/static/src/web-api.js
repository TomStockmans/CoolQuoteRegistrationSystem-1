let id = 0;

function getId(){
  return ++id;
}

let quotes = [];

export class WebAPI {
  saveQuote(quote) {
      quotes.push(quote);
      console.log(quotes);
  }
}
