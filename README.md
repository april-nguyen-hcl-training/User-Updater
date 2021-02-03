# User Updater

User Updater is a Java Spring MVC web application that will retrieve users based on their user ID. The retrieved user data will then be edited in a form and updated in the database. The project uses Spring MVC with Hibernate, log4j, MySQL Connector, JSP, Maven, Apache Tomcat, and a MySQL database.

## Installation

Use [docker](https://docs.docker.com/get-docker/) to install.

```bash
docker-compose --file docker/compose.yaml up --build -d
```

## Usage

In browser, go to [link](http://localhost:8081/users).

## Stopping

```bash
docker-compose --file docker/compose.yaml down
```

## License
[MIT](https://choosealicense.com/licenses/mit/)