## Test Project For Java Engineer Role

This repo contains the solution to question for the java developer test, where I built a backend service and
a UI service, and made them interact using a Jax RS API client.

My submission is in 4 parts:
1. the code repo and the `readme` file for setting up and running the project
2. the [DISCUSSION.md](https://github.com/Oluwatodimu/book-catalogue/blob/main/DISCUSSION.md)
3. the postman collection that can be accessed [here](https://www.postman.com/lively-firefly-891824/todimu-workspace/collection/b8na2b2/payu-management-api?action=share&creator=18629385)
4. the UI, which can be viewed in the browser, using the url `localhost:8090`.

### Setting up the project
- Clone the repo.
- Ensure Java 8 is installed on your machine.
- Ensure you have *maven* installed.
- Ensure you have *docker* installed and running.
- Navigate to the cloned project directory.

### Building the fat jar files and docker images
Since there are two applications, we have to build and dockerize jar files for both of them. These
jar files will contain all the necessary dependencies to run them.

To build the jar file for the UI spring boot application and dockerize it, the following steps have to be followed:
1. navigate to the client directory using `cd client`, this should be typed in the cloned project main directory terminal.
2. run the maven command to package the application into a jar file. This can be achieved using the command
`mvn clean package`
3. Once this command runs successfully we have to package the jar file into a docker image, and we can achieve this using the command
`docker build . -t payu/web-client:v1` (ensure you are in the same directory as the client dockerfile).
4. navigate back to the main project directory by running the command `cd ..`, once the docker image for the web client has been built

Once this is successfully done, the next thing we have to do is to build and dockerize the jar file for the backend service,
which can be done by following the steps below:
1. navigate to the backend server directory using `cd management`, this should be typed in the cloned project main directory terminal.
2. run the maven command to package the application into a jar file. This can be achieved using the command
   `mvn clean package`
3. Once this command runs successfully we have to package the jar file into a docker image, and we can achieve this using the command
   `docker build . -t payu/management-api:v1` (ensure you are in the same directory as the backend server dockerfile).
4. navigate back to the main project directory by running the command `cd ..`, once the docker image for the backend server has been built.

After doing this, we can run the application now.

### Running the application
- navigate to the docker-compose directory in the cloned project.
- run the application using the following command `doocker compose up -d`. 
- once this is done and the containers are running, you can go the browser to view the application at this port `localhost:8090`.
- to stop the containers you can use the command `docker compose down`.

*NB*: You can also run the application by running the jar files directly in their directories. This can be achieved using 
the command `mvn spring-boot:run`, just start the backend server before the web client. I recommend using the docker approach since 
we are working with two separate applications interacting with each other, and that is best within the
same network. Plus also, it is OS agnostic so easier and fast deployment. I also wrote some code to ensure the backend service
is fine (healthy) before running the web client, making one dependent on the other

### Testing the backend application APIs
After you have successfully started the application, you can use this link to take you to the postman collection for viewing and testing the backend APIs
[here](https://www.postman.com/lively-firefly-891824/todimu-workspace/collection/b8na2b2/payu-management-api?action=share&creator=18629385)


