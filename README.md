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
4. Go to [Swagger](http://localhost:8080/swagger-ui.html) and play around

### Examples

1. Save/Create new entry 
```bash
curl -X POST \
  http://localhost:8080/materials \
  -H 'Accept: application/json' \
  -H 'Accept-Encoding: gzip, deflate, br' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
        "formula": "In2Te324",
        "color": "Black",
        "bandGap": 1
}' 
```

2. Find all records with band gap [0, 3]
```bash
curl -X GET \
  'http://localhost:8080/materials?compound=Ga&bandGap=0,3' \
  -H 'Accept: application/json' \
  -H 'Accept-Encoding: gzip, deflate, br'
```

3. Find all records with `Ga` as compound
```bash
curl -X GET \
  'http://localhost:8080/materials?compound=Ga' \
  -H 'Accept: application/json' \
  -H 'Accept-Encoding: gzip, deflate, br'
```

4. Find all with `Ga` and band gap [0, 3]
```bash
curl -X GET \
  'http://localhost:8080/materials?compound=Ga&bandGap=1,3' \
  -H 'Accept: application/json' \
  -H 'Accept-Encoding: gzip, deflate, br' 
```
### Todos
 - Write MORE Tests
License
----
MIT