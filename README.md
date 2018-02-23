# ReST lookup Service

In real-life use case I would have gladly put [postgrest](https://github.com/begriffs/postgrest) into prouction*. 

# General implementation idea

  - Try to provide as much flexibilty as possible to the user for searching desired characteristics
  - Implement minimal own code
  - Try to validate as much input as possible

### Tech

* Spring Boot
* MyBatis
* PostgreSQL
* Swagger
* Docker
 
### Running

#### Docker
1. Install the dependencies.
* [Docker](https://docs.docker.com/install/)
* [Docker Compose](https://docs.docker.com/compose/install/)

2. Build project
```sh 
cd <PROJECT_ROOT_DIRECTORY>
```

3. 
```sh
docker-compose up
```
4. Go to http://localhost:8080/swagger-ui.html and play around

#### Local Env
1. Install the dependencies.
* [PostgreSQL 10.2+](https://www.postgresql.org/download/)
* [Maven 3.5.2+](https://maven.apache.org/download.cgi)
2. Make sure to adjust `<PROJECT_ROOT>/src/main/resources/application.properties` to set correct values for PG local connection.
3. Run app
```sh
mvn spring-boot:run
```
4. Go to http://localhost:8080/swagger-ui.html and play around

### Todos
 - Write MORE Tests
License
----
MIT