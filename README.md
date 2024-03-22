# Project Name: Mocker

## Introduction
This project is a Java Spring API application that provides an endpoint to create a new post. The application inserts the post data into a PostgreSQL database and makes an outbound HTTP call to fetch the current time from the World Time API. The API returns the created post ID along with the response body received from the World Time API. This application runs in two modes: RECORD and REPLAY.
In RECORD mode, real outbound request is made and real database insertion is done. But, in REPLAY mode this runs without any external services (HTTP/DATABASe). REPLAY mode basically mocks the RECORD mode. An ENV variable (HT_MODE) decides in which mode the application runs.

## Features (RECORD MODE)
- Exposes a POST endpoint `/api/createNewPost` to create a new post.
- Inserts post data into a PostgreSQL database.
- Makes an outbound HTTP call to the World Time API to fetch the current time.
- Returns the created post ID and the response body from the World Time API.

## Technologies Used
- Java
- Spring Boot
- PostgreSQL
- Maven
- HttpClient
- ByteBuddy

## Env variables
- Before you start setting up the application there is one thing to note. Database and http configurations are picked from
  ENV variables. You need to set the ENV variables in order to run the application. Here is the list of them:
1. SPRING_DATASOURCE_URL
2. SPRING_DATASOURCE_USERNAME
3. SPRING_DATASOURCE_PASSWORD
4. HTTP_ENDPOINT (This endpoint will be called after saving the post to DB and response will be returned)
5. HT_MODE (RECORD/REPLAY)

## Setup Instructions (without docker)
1. Clone the repository to your local machine.
2. Install PostgreSQL and create a database named `your_database_name`.
3. Update the `application.properties` file in the `src/main/resources` directory with your PostgreSQL database credentials and URL.
4. Open the project in IntelliJ IDEA or your preferred IDE.
5. Build the project using Maven to download dependencies.
6. Run the application: `HT_MODE=REPLAY java -javaagent:target/Mocker-0.0.1-SNAPSHOT-agent.jar -jar target/Mocker-0.0.1-SNAPSHOT.jar`

## Setup Instructions (with docker)
1. Make sure you have Docker installed and setup properly on your local machine.
2. Clone the repository.
3. Change the ENV variables in the Dockerfile as required.
4. Run `docker-compose up --build` and you are good to go.

## API Endpoints
### Create New Post
- **URL:** `/api/createNewPost`
- **Method:** POST
- **Request Body:**
  ```json
  {
    "post_name": "<some-unique-name>",
    "post_contents": "<some-random-string>"
  }
  ```
- **Success Response:**
  - **Status Code:** 200 OK
  - **Response Body:**
    ```json
    {
      "db_post": "<db row with id>",
      "http_outbound": "<response_body_received_from_world_time_api>"
    }
    ```
- **Error Response:**
  - **Status Code:** 500 Internal Server Error
  - **Response Body:**
    ```json
    {
      "error": "<exception_message>"
    }
    ```

## Dependencies
- Spring Boot Starter Web: To create RESTful APIs.
- Spring Boot Starter Data JPA: To interact with the PostgreSQL database.
- Spring Boot Starter Test: For unit and integration testing.
- PostgreSQL Driver: To connect to the PostgreSQL database.
- ByteBuddy: To patch the http/db libraries

## Project Structure
```
SocialMedia/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── project/
│   │   │           └── socialmedia/
│   │   │               ├── controller/
│   │   │               │   └── PostController.java
│   │   │               ├── model/
│   │   │               │   └── Post.java
│   │   │               ├── repository/
│   │   │               │   └── PostRepository.java
│   │   │               ├── service/
│   │   │               │   └── PostService.java
│   │   │               └── Application.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── java/
│           └── com/
│               └── project/
│                   └── socialmedia/
├── README.md
└── pom.xml
```
