export class App {

  configureRouter(config, router) {
    this.router = router;

    config.map([
      {
        route: "",
        moduleId: "quotes/list",
        nav: true,
        title: "All Quotes",
        name: "home"
      },
      {
        route: "quotes/add",
        moduleId: "quotes/add/add",
        nav: true,
        title: "Add new Quote"
      },
      {
        route: "about",
        moduleId: "about/about",
        nav: true,
        title: "About"
      }
    ]);
  }
}
