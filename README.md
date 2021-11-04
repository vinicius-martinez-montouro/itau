# Project Validate Password

This API was used to validate password in a parking lot, where the following technologies were used:

 - Java 11
 - Spring boot
 - Lombook
 - Maven

# Files

Docker: Docker compose files are located in the following directory:
itau/selective.process/docker
	
## Installation roadmap

>Necessary tools:
- Maven 3.6.2
- JDK 11
- Docker
- Eclipse or Intelij
- Plugin Lombok and Docker
>Generate project package
-Inside the folder selective.process run the command: mvn clean package

>Upload application
>mvn spring-boot:run
-Inside the folder selective.process execute the command: Test if the API has gone up through the 
URL http://localhost:80802/password/v1/validate

## Docker

>Docker Compose:
-The docker compose is located in the following selective.process/docker directory. This is responsible for uploading the application.
The exposed ports are for the API https://localhost:8082.

## Up Application without environment
-Run: To upload the account with the images, just type at the command prompt inside the folder parking/docker:
	**`docker-compose up -d --build`**

## Swagger
-The project documentation can be accessed through the 
URL http://localhost:8082/swagger-ui.html


## Solução:

Foi construido uma API para realizar a validação de uma senha enviada por uma requisição http POST. A senha não foi criptografada, pois não achei a necessidade para essa solução, uma vez que a proposta se trata de apenas uma checagem de password valido.
Toda a checagem é realizada na cadamada de serviço, e conforme a string enviada não esteja dentro dos requisitos, é retornado body com as mensagens de quais requisitos não foram atendidos e status code 412, como erro de precondição.
As validações de senha foi utilizado regex e está foi implementada em uma classe utils, pois poderia ser utilizada em outros lugares da aplicação no futuro e caso fosse utilzado em  outras aplicações, é mais fácil transformar em uma lib.