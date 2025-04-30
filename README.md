Spring Akka App
===============

A simple Spring Boot application integrated with Akka actors. It demonstrates how to:

-   Wire an **Akka `ActorSystem`** into a Spring context

-   Create and configure **actors** as Spring beans

-   Expose **REST endpoints** that send messages to actors and return their replies

Features
--------

-   **`/greet?name={name}`**: Returns a greeting message from the `HelloActor`.

-   **`/count`**: Increments and returns a counter maintained by the `CounterActor`.

Technology Stack
----------------

-   Java 17

-   Spring Boot 3.4.5 (`spring-boot-starter-web`)

-   Akka 2.6.20 (`akka-actor` + `akka-slf4j`)

-   SLF4J / Logback for logging

-   Maven for build and dependency management

Project Structure
-----------------

```
src/
└─ main/
   ├─ java/com/pradeepl/spring_akka_app/
   │  ├─ SpringAkkaAppApplication.java      # Spring Boot entry point
   │  ├─ config/
   │  │  └─ AkkaConfig.java                # ActorSystem & ActorRef beans
   │  ├─ spring/
   │  │  ├─ SpringExtension.java           # Spring→Akka extension
   │  │  └─ SpringActorProducer.java       # Actor producer via Spring
   │  ├─ actors/
   │  │  ├─ HelloActor.java                # Greeter actor
   │  │  └─ CounterActor.java              # Stateful counter actor
   │  └─ controllers/
   │     ├─ GreetingController.java        # `/greet` endpoint
   │     └─ CounterController.java         # `/count` endpoint
   └─ resources/
      └─ application.conf                  # Akka config (optional)

```

Prerequisites
-------------

-   JDK 17

-   Maven 3.6+

Build & Run
-----------

1.  **Build**

    ```
    mvn clean package

    ```

2.  **Run via Spring Boot**

    ```
    mvn spring-boot:run

    ```

3.  **Run the packaged JAR**

    ```
    java -jar target/spring-akka-app-0.0.1-SNAPSHOT.jar

    ```

Usage
-----

-   **Greet**

    ```
    curl "http://localhost:8080/greet?name=Pradeep"
    # → Hello, Pradeep!

    ```

-   **Count**

    ```
    curl http://localhost:8080/count
    # → 1
    curl http://localhost:8080/count
    # → 2

    ```

Customization
-------------

-   **Add new actors**: Create a Spring component with prototype scope and register its `ActorRef` in `AkkaConfig`.

-   **Expose new endpoints**: Define a Spring `@RestController` and inject the corresponding `ActorRef` bean.

License
-------

This project is released under the MIT License.