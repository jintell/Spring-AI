# Getting Started

### Spring AI

This application is based on Spring Boot app using spring boot 3.2.5, gradle 8.7 build tool and java 17.

1. It uses the latest gradle artifact `org.springframework.ai:spring-ai-openai-spring-boot-starter`,
2. `org.springframework.boot:spring-boot-starter-web` to run the application as micro service,


**Note:** You need to configure your Open Ai Api key and save in the environment variable  for the spring app, 
and also for the env for the JUnit for test purposes. Make sure the Ai-Test.MD is available with the prompts  


# Running a Test
* Please check under the test package -> service -> OpenAiServiceTest
* Run the getAnswer() test, by clicking the play sign on the left side.
* You can also run from the terminal: ./gradlew test. Bfore running it set the env variable in the command line, spring.ai.openai.api-key=<YOUR_OPEN_API_API_KEY>

# Build the artifact

`./gradlew clean build -x test`

# Run the built GraphQL Server

`java -jar build/libs/spring-ai-intro-0.0.1-SNAPSHOT.jar`

# Alternatively, Build and Run with a Single gradle command.

`./gradlew spring-boot:run`

**Note:** This option will not build the artifact (spring-ai-intro-0.0.1-SNAPSHOT.jar) for you

# Stop the server

`Control+C`

### Guides

The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
