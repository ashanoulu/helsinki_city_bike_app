# Helsinki City  Bike App - Rest API

### Java - 17.0
### Spring Boot - 3.0

## Steps to getting started

1. Change the database credentials in application.properties. 
(To import data and SQL script please refer the data imports project.)
i.e - You can auto generate DB by changing following in application.properties, but it is not recommended for this project.
```bash
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=true
```
2. If you use maven to build use following 
```bash
mvn spring-boot:run
```
3. If you use Intelij or any other IDE it's easy to build and run using the IDE functionalities (Main Class - DemoApplication.java)

#### Run in Docker

1. First create network to communicate DB and Application
```
docker network create spring-mysql-net <-- you can use any name
```
3. Build & Run MySql Docker image after completed, login to DB using docker console and execute SQL script. 
```
docker run --name mysql -d -e MYSQL_ROOT_PASSWORD=root -v mysql:/var/lib/mysql --network spring-mysql-net mysql:8
mysql -uroot -proot <-- login to DB
```
3. Change the MySql connection data in application.properties (I have clearly indicated both connection data)
4. Build .jar using maven (Can easily build with IDE)
5. Build docker image (Rest API)
```
docker build -t restapi .
```
6. Start docker container 
```
docker container run --network spring-mysql-net --name mysql-restapi-container -p 8080:8080 -d restapi
```

### API Docs
After you start the project you can simply access the API Docs via Swagger UI

Follow the below URL - 

    http://${your_host}:${port}/api/v1/swagger-ui/index.html#/
#### Architecture
In this application, I followed MVC high level architecture by adding a service layer. 
Mainly you can find there are four groups

1. Controllers
2. Models
3. Repositories
4. Service

##### Upcoming features (features under developing)
1. Unit testings
2. E2E Testings (Cypress)
3. Live Demo (Host in a cloud environment)
