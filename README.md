## Products

Manage the products

### Install dependencies

* Install docker services

`docker-compose up -d`

### Run locally

* Run on server port 8080

`./mvnw spring-boot:run`

### Products

`curl --location --request GET 'localhost:8080/product' \ --header 'AUTH_USER: example'`
