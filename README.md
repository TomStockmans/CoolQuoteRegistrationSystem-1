# Cool Quote Registration System

[![Build Status](https://travis-ci.org/SoftwareSandbox/CoolQuoteRegistrationSystem.svg?branch=prod)](https://travis-ci.org/SoftwareSandbox/CoolQuoteRegistrationSystem)

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

## How the dev-deploy cycle works now
Note: We don't have automatic branch promotion so we have to do this manually.

1. Make your changes.
2. Build locally with `./gradlew buildDist stageUI build`
3. Build a docker image with `docker build . -t swsb/cqrsapp:1.0-SNAPSHOT`
4. Manually test your changes with `docker-compose up`
5. Merge your branch with prod with `git merge origin/prod`
6. If you had any conflicts, merge them and go back to step 2.
7. Push your branch to github
8. Wait for a successful Travis build of your branch
9. Merge your branch on to prod with `git checkout prod && git merge <yourbranch>`
10. Push prod to github
11. Travis will pick up the prod branch, and on a successful gradle build, Travis will build a docker image from your successfully built .ear file and push it to both [DockerHub](https://hub.docker.com/r/swsb/cqrsapp/)'s and [Heroku](https://dashboard.heroku.com/apps/cool-quote-registration-system)'s registries.
12. Heroku will pick up the newly pushed docker image and will restart the container with the new docker image.

### Travis
Check out Travis' excellent [documentation](https://docs.travis-ci.com/) and our [.travis.yml](.travis.yml) file.

## No Procfile necessary for Heroku
Because we're actually deploying with Travis by pushing to the Heroku registry.

## Deploy manually
Preferably **don't** deploy manually, because there aren't necessarily any code changes pushed to our code repository.

First log in to the heroku registry with `docker login -u=herokuuser -p=herokuauthtoken registry.heroku.com`, or with the CLI `heroku container:login`.

Then make sure you create a tag based off your earlier built docker image (see above) with `docker tag swsb/cqrsapp:1.0-SNAPSHOT registry.heroku.com/cool-quote-registration-system/web`.

And push that tag with `docker push registry.heroku.com/cool-quote-registration-system/web`.

## Troubleshooting production
### Heroku Config vars for every Docker ENV var
It's really important that there are Heroku config variables for every environment variable our docker container needs.

These are entered in [heroku's settings tab](https://dashboard.heroku.com/apps/cool-quote-registration-system/settings).

Miss one, either by not listing a new one, or having a typo in one of them, and the app won't work anymore.

### Consulting Heroku logs
Either install the [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli) and type `heroku logs -a cool-quote-registration-system`, or log in to the Heroku dashboard and click on [_More > Logs_]([here](https://dashboard.heroku.com/apps/cool-quote-registration-system/logs)).

### Deploying the latest production build locally
First pull the latest successfully deployed docker image with `docker pull swsb/cqrsapp`.

Then change [docker-compose.yml](docker-compose.yml) to use the `swsb/cqrsapp` image instead of the locally built `:1.0-SNAPSHOT` tag.

And run `docker-compose up`.

## Deploying to Heroku
[Deploying using Gradle](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku)

[Deploying using a Docker Container](https://devcenter.heroku.com/articles/container-registry-and-runtime)
