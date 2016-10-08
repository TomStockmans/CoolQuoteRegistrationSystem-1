export class App {

  configureRouter(config, router) {
    this.router = router;

    config.map([
      { route:"", moduleId:"quotes/list",
        title:"All Quotes", nav:true, name:"home" },

      { route:"about", moduleId:"about/about",
        title:"About", nav:true}
    ]);
  }
}
