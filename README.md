# Spring Boot 3.1 HTTP Basic Authentication Demo

## Summary

This is a demo for securing a REST interface with Spring Boot 3.1 and Spring Security.
This application provides three REST endpoints:

* Get a list of todos, free for all
* Add an item to the list of todos (authenticated)
* Get username of the user (authenticated)

The list of todos is only saved in memory without persisting (e.g. database). 

## Compilation

To compile the project within its root directory running Windows use:

```cmd
> mvnw.cmd clean install
```

To compile the project within its root directory running Mac or Linux use:

```bash
$ ./mvnw clean install
```

## Running

To run the project within its root directory running Windows use:

```cmd
> mvnw.cmd spring-boot:run
```

To compile the project within its root directory running Mac or Linux use:

```bash
$ ./mvnw spring-boot:run
```

## Endpoints

1. `GET /todos`: Get list of all todos in JSON

Example call and answer:
```http request
GET http://localhost:8080/todos

[
    {
        "name": "Do dishes",
        "created": "2023-01-20T16:41:17.687349226Z",
        "due": "2023-01-24"
    }
]
```

2. `POST /todos`: Get list of all todos in JSON

Example call and answer:
```http request
POST http://localhost:8080/todos
Content-Type: application/json
Authorization: Basic YWxpY2U6YWxpY2U=

{
    "name": "Do dishes",
    "due": "2023-01-24"
}

{
    "name": "Do dishes",
    "created": "2023-01-20T16:48:49.305823981Z",
    "due": "2023-01-24"
}
```

3. `GET /me`: Get username of current user

Example call and answer:
```http request
GET http://localhost:8080/me
Authorization: Basic Ym9iOmJvYg==

bob
```

## Authentication with HTTP

Simple authentication with HTTP Basic was implemented here. This should not be used in production,
especially not without SSL, since the passwords are sent unencrypted and can therefore be read.

In order to send the login via HTTP, the *Authorization* header must be set with the value `Basic`,
space, username, colon and password, whereby username, colon and password are base64 encoded.

All users are defined in [UserDetailServiceImpl](src/main/java/com/example/authenticationdemo/service/UserDetailServiceImpl.java)
and passwords are hashed with Bcrypt according to [SecurityConfiguration](src/main/java/com/example/authenticationdemo/config/SecurityConfiguration.java).

For *alice:alice*
```http request
Authorization: Basic YWxpY2U6YWxpY2U=
```
and for *bob:bob*
```http request
Authorization: Basic Ym9iOmJvYg==
```

## Launch Swagger UI

The full functionality can be tested with the Swagger UI. This is
available at <http://localhost:8080/swagger-ui.html>.

The endpoints <http://localhost:8080/me> and POST on <http://localhost:8080/todos> require one
authentication. This can be done with the *Authorize* button at the top right.
