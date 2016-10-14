import { Router, RouterConfiguration } from 'aurelia-router';

export class App {

  router: Router;

  configureRouter(config: RouterConfiguration, router: Router) {
    config.title = 'Contacts';
    config.map([
      { route: '', moduleId: 'quotes', title: 'Quotes' },
      { route: 'addQuoteBlock', moduleId: 'quote-block', title: 'Add quote block' },
      { route: 'quotes', moduleId: 'quotes', title: 'Quotes' },
      { route: 'searchquotes', moduleId: 'search-quotes', title: 'SearchQuotes' }
    ]);

    this.router = router;
  }
}

