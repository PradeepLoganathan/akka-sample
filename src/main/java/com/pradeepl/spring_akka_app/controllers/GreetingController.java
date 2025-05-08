package com.pradeepl.spring_akka_app.controllers;

import akka.actor.ActorRef;
import akka.pattern.AskTimeoutException;
import akka.pattern.Patterns;
import akka.pattern.AskTimeoutException;

import com.pradeepl.spring_akka_app.actors.HelloActor.GreetCommand;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;
import java.util.concurrent.CompletionStage;

@RestController
public class GreetingController {

    private final ActorRef helloActor;
    private final Duration askTimeout;

    public GreetingController(
            @Qualifier("helloActorRef") ActorRef helloActor,
            @Value("${akka.ask.timeout:5s}") Duration askTimeout) {
        this.helloActor = helloActor;
        this.askTimeout = askTimeout;
    }

    @GetMapping("/greet")
    public CompletionStage<String> greet(
            @RequestParam(defaultValue = "World") String name) {
        return Patterns
            .ask(helloActor, new GreetCommand(name), askTimeout)
            .thenApply(obj -> (String) obj)
            .exceptionally(throwable -> {
                // Handle specific exceptions, like timeout
                if (throwable instanceof AskTimeoutException) {
                    return "Greeting service did not respond in time.";
                }
                // Handle other exceptions
                return "An error occurred: " + throwable.getMessage();                
            });
    }
}
