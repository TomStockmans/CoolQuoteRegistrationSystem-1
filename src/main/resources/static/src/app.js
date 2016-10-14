import { Router, RouterConfiguration } from 'aurelia-router';

export class App {

  router: Router;

  configureRouter(config: RouterConfiguration, router: Router) {
    config.title = 'CQRS - Cool Quote Registration System';
    config.map([
      { route: '', moduleId: 'quotes', title: 'Quotes' },
      { route: 'addQuoteBlock', moduleId: 'quote-block', title: 'Add quote block' },
      { route: 'addQuoteBlockSpecial', moduleId: 'components/quote-block-special/quote-block-special', title: 'Add quote block special' },
      { route: 'quotes', moduleId: 'quotes', title: 'Quotes' },
      { route: 'searchquotes', moduleId: 'search-quotes', title: 'SearchQuotes' }
    ]);

    this.router = router;
  }
}

