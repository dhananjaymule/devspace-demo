Java Food Ordering Application for OpenShift Dev Spaces
This is a simple Spring Boot application designed to demonstrate a typical development and deployment workflow in an environment like OpenShift.
Features
Command-Line Interface (CLI): On startup, the application connects to a PostgreSQL database, displays a menu of food items, and prompts the user to place an order.
PostgreSQL Backend: Stores a table of food_items with their prices. The database is automatically initialized on first run.
REST API with Swagger UI: Includes a simple REST endpoint /status to check if the application is running. The Swagger UI at /swagger-ui.html provides interactive API documentation.
How to Run Locally (Using Docker Compose)
This is the recommended first step to ensure everything is working before deploying to OpenShift.

Prerequisites:
Java 17+
Maven 3.8+
Docker and Docker Compose

Steps:
Build the Maven Project:
This compiles the code and packages it into a .jar file in the target/ directory.
$ mvn clean package


Run with Docker Compose:
This command will build the Docker image for the application and start both the application and the PostgreSQL containers.
$ docker-compose up --build


Interact with the Application:
CLI: You will see the menu and ordering prompt directly in your terminal where you ran the docker-compose command.
Swagger UI: Open your web browser and navigate to http://localhost:8080/swagger-ui.html.
Status Endpoint: You can also check the status directly at http://localhost:8080/status.
Deployment to OpenShift
Here’s how this project structure helps you test your OpenShift goals:
Maven Build:
You can run mvn clean package inside your OpenShift dev space terminal to build the application JAR.
Exposing PostgreSQL:
In OpenShift, you would typically deploy a PostgreSQL instance from the Developer Catalog. OpenShift will provide you with the necessary connection details (service name, database name, user, password) as secrets.
You will then configure your application's deployment to use these secrets as environment variables, just like we did with SPRING_DATASOURCE_URL in the docker-compose.yml file.
Doing Maven Build on Application Code:
You can set up a build configuration in OpenShift (like a Source-to-Image or S2I build) that points to your Git repository. This build will automatically run the Maven build (mvn package) and create a container image for you.
Exposing Application and Swagger UI:
Once your application is deployed and running in a pod, you will create a Route in OpenShift. A Route is a publicly accessible URL that directs traffic to your application's service on port 8080.
Accessing https://<your-openshift-route-url>/swagger-ui.html will then show you the Swagger UI, proving that your application is successfully exposed to the internet.
