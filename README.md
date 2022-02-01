## Technologies Used

Java, Spring boot, Swagger-ui, Maven, Jacoco, Docker-compose, MySQL

## Building the application
You can use: `make build` or alternatively
`mvn clean install`

To run unit tests, you can do `make test` or alternatively
`mvn clean test`

The code coverage will be available at target/site/jacoco/index.html

## Application Setup
Run the mysql using the docker-compose. 
`docker-compose up --detach` runs it in detach mode

The credentials for mysql are in the docker-compose file.
The initial dump files are in `mysql-dump` directory

You can use `make run` or alternatively
`mvn spring-boot:run`

Once done, you can do `Ctrl+c` to close the running application 
and `docker-compose down` to bring down the mysql

By default, the application runs on port 8080. You can modify this in application.properties file located  at src/main/resources

## Swagger Endpoint is available at

http://localhost:8080/swagger-ui/

One advantage with swagger is it provides some default json that might work.

Here are the list of available endpoints:
![Available endpoints](img.png)




