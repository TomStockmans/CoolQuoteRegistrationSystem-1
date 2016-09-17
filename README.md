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

There now should be an instance running at 192.168.99.100:27017.

## Querying MongoDB
`brew install mongodb` to get at least a mongo shell.

Install the `Mongo Plugin` in IntelliJ.


