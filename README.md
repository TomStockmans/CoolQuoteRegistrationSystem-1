# Cool Quote Registration System

## Stack
Frontend: [Aurelia](http://aurelia.io/)

Backend: [SpringBoot](http://projects.spring.io/spring-boot/) with [MongoDB](https://www.mongodb.com/)

## Agreements/Constraints
The UI is going to transform its internal domain model to the domain model of the backend, and just use the REST API as a simple CRUD API.

Validation of the backends domain model happens in the backend.

Validation of the UI domain model happens in the UI.

Reasoning behind this is that first and foremost we want to play with Aurelia.

## Docker stuff
Install Docker. (either with toolbox or native)

Run `docker-compose up`.

There now should be an instance running at `192.168.99.100:27017`.

## Querying MongoDB
`brew install mongodb` to get at least a mongo shell.

Install the `Mongo Plugin` in IntelliJ.

Add `192.168.99.100:27017` as a new connection, _et voila_, happy querying.

As an alternative, you can also install [RoboMongo](https://robomongo.org/)

## Serving static content
We are now only serving static content, and therefore we don't need an `IndexController` anymore.

Thymeleaf serves `index.html` straight from the `resources/static` dir.

This is why `application.properties` has properties:

    spring.jersey.applicationPath=/api
    spring.jersey.type=filter

The `spring.jersey.type=filter` property will run Jersey as a Filter instead of a Servlet.

The other thing I had to do was add the `spring-boot-starter-thymeleaf` dependency, so that there's an automagic ViewResolver that tries to find a matching filename in `/resources/static`.

## Info on the Aurelia App
I generated it using `au new --here` and modifying the default names to contain CQRS instead of default or whatever.

To be able to work with this way of Aurelia app, install the aurelia-cli.

```
npm install -g aurelia-cli
```

Or follow [this guide](http://aurelia.io/hub.html#/doc/article/aurelia/framework/latest/the-aurelia-cli/1) for more info.

### IntelliJ pro-tip
Mark `src/main/resources/static/node_modules` as _Excluded_. :+1:

## IntegrationTest magic
### Overriding application.properties
Other than [src/main/resources/application.properties](src/main/resources/application.properties), there's a [src/test/resources/application.properties](src/test/resources/application.properties) which overriding the main one at test runtime.

In the latter, the `spring.data.mongodb.database` property is set to `test`.

This is why we simply use the `Application` and `JerseyConfig` configuration classes of production.

### Resource proxies
We're calling our _Resource_ with the `jersey-proxy-client` framework.

This is why we're not `@Autowiring` our own _Resource_, but instead using a `WebResourceFactory` to proxy our _Resource_, and do all the actual http calls, marshalling and error handling via `jersey-client`.
