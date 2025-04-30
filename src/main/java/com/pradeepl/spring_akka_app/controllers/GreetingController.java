package com.pradeepl.spring_akka_app.controllers;

import akka.actor.ActorRef;
import akka.pattern.Patterns;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;
import java.util.concurrent.CompletionStage;

@RestController
public class GreetingController {

    private final ActorRef helloActor;

    public GreetingController(@Qualifier("helloActorRef") ActorRef helloActor) {
        this.helloActor = helloActor;
    }

    @GetMapping("/greet")
    public CompletionStage<String> greet(
            @RequestParam(defaultValue = "World") String name) {
        return Patterns
            .ask(helloActor, name, Duration.ofSeconds(5))
            .thenApply(obj -> (String) obj);
    }
}
