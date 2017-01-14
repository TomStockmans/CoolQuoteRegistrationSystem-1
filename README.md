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

### Aurelia CLI
To be able to work with this way of Aurelia app, install the aurelia-cli as follows:

```
npm install -g aurelia-cli
```

Or follow [this guide](http://aurelia.io/hub.html#/doc/article/aurelia/framework/latest/the-aurelia-cli/1) for more info.

### Generation
I generated it using `au new --here` and modifying the default names to contain CQRS instead of default or whatever.

### IntelliJ pro-tip
Mark `src/main/resources/static/node_modules` as _Excluded_. :+1:

### Building and starting
First, since node_modules was excluded, you have to install all the dependencies.
From the static dir, run:

```
npm install
```

Next, from the static dir, run the app via the CLI:

```
au run
```

### Funky stuff
#### Notifications with aurelia-notification and humane.js
We're using [aurelia-notification](https://github.com/SpoonX/aurelia-notification), a non-official plugin for Aurelia which wraps [humane.js](http://wavded.github.io/humane-js/) to provide notifications that automatically disappear.

This required us to add a dependency on [aurelia-18n](https://github.com/aurelia/i18n), which required a dependency on `i18next` and `i18n-xhr-backend`.

There's stuff configured in `main.js` for both i18n and notifications.

Whenever you want to change the humanejs theme, you'll have to modify

```
main.js:

  .plugin('aurelia-notification', config => {
    ...
        notifications: {
          'success': 'humane-jackedup-success',
          'error': 'humane-jackedup-error',
          'info': 'humane-jackedup-info'
        }
    ...
```

```
index.html:

<link rel="stylesheet" href="node_modules/humane-js/themes/jackedup.css">

```

```
aurelia.json:

{
    "name": "humane-js",
    "path": "../node_modules/humane-js",
    "main": "humane",
    "resources": [
      "themes/jackedup.css"
    ]
}
```
## IntegrationTest magic
### Overriding application.properties
Other than [src/main/resources/application.properties](src/main/resources/application.properties), there's a [src/test/resources/application.properties](src/test/resources/application.properties) which overriding the main one at test runtime.

In the latter, the `spring.data.mongodb.database` property is set to `test`.

This is why we simply use the `Application` and `JerseyConfig` configuration classes of production.

### Resource proxies
We're calling our _Resource_ with the `jersey-proxy-client` framework.

This is why we're not `@Autowiring` our own _Resource_, but instead using a `WebResourceFactory` to proxy our _Resource_, and do all the actual http calls, marshalling and error handling via `jersey-client`.


## Deploying to Heroku
[Deploying using Gradle](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku)

[Deploying using a Docker Container](https://devcenter.heroku.com/articles/container-registry-and-runtime)

### Stuff to do to be able to deploy our app as a docker container on Heroku

* create a dockerhub registry
* have travis build and push an image to the registry
* configure heroku with the `latest` tag of the dockerhub image
* configure heroku to _deploy after successful travis build_
* and I guess also fix our Aurelia app to use a proper _baseUrl_ or something :smile: