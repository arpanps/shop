# Shop Service
A Example RESTful web service for managing packages consisting of one or more products. Microservice makes use of an online [product service](products-service.herokuapp.com/api/v1/products) to get products.

Check [shop-ui repo](https://github.com/arpanps/shop-ui) for basic UI which consumes Shop service rest APIs.

## Running Service
### Docker

You need to have [Docker installed](https://docs.docker.com/v17.09/engine/installation/)

Following command will pull and run the image on machine. This will run shop service with default configuration 

`docker run -p 8080:8080 arpanps/microservice-shop:0.0.1-SNAPSHOT`

The API documentations will be available at the path /api-docs

`http://localhost:8080/shop/api-docs/`

Interact with application API specification and exercise the endpoints via Swagger UI. Access Swagger UI using below link

`http://localhost:8080/shop/swagger-ui/index.html`

### For Development
* Prerequisite - git, java, maven 

* Clone Repo

	`git clone https://github.com/arpanps/shop.git`

* Go inside project directory

	`cd shop`

* Build Project with Maven

	`mvn install`

* Run Service using Maven  
 
	`mvn spring-boot:run`

#### Publishing Docker Image

* Package project

	`mvn clean package`

* Build the docker image (Use your username instead of arpanps)

	`docker build -t arpanps/microservice-shop -f etc/docker/Dockerfile .`

* Login with your Docker Id
	
	`docker login`
	
* Tag the image

	`docker tag arpanps/microservice-shop arpanps/microservice-shop:0.0.1-SNAPSHOT`

* Push the image to docker hub

	`docker push arpanps/microservice-shop:0.0.1-SNAPSHOT`

