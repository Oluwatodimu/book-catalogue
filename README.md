## Test Project For Java Engineer Role

This repo contains the solution to question for the java developer test.

My submission is in 3 parts:
1. the code repo and the `readme` file for setting up and running the project
2. the [DISCUSSION.md](https://github.com/Oluwatodimu/book-catalogue/blob/main/DISCUSSION.md)
3. the postman collection that can be accessed [here](https://www.postman.com/lively-firefly-891824/todimu-workspace/collection/b8na2b2/payu-management-api?action=share&creator=18629385)

### Running the application
I have provided two methods to run the application:

1. Using Maven to package the application as JAR files and running them.
2. Using Docker Compose to run the JAR files, which have been built as Docker images and published on Docker Hub.

### Option 1

*dependencies:* install java 8+ and maven

#### Steps to Run the Applications
1. clone the repo
2. navigate to the project directory

Running the management (backend) service:
1. navigate to the `management` directory
2. build the application with `mvn clean package`
3. run the application with `mvn spring-boot:run`

Running the client (frontend) service:
1. navigate to the `client` directory
2. build the application with `mvn clean package`
3. run the application with `mvn spring-boot:run`

That's it !!!, you can now view the application on the browser at `localhost:8090`

### Option 2

*dependencies:* install and run docker

To use docker, this is super simple:
1. navigate to the project directory.
2. navigate to the `docker-compose` directory
3. run the application with `docker compose up -d`

That's it !!!, you can now view the application on the browser at `localhost:8090`



### Testing the backend application APIs
After you have successfully started the application, you can use this link to take you to the postman collection for viewing and testing the backend APIs
[here](https://www.postman.com/lively-firefly-891824/todimu-workspace/collection/b8na2b2/payu-management-api?action=share&creator=18629385)


