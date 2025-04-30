package com.pradeepl.spring_akka_app.controllers;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

@RestController
public class CounterController {

    private final ActorRef counterActor;

    public CounterController(@Qualifier("counterActorRef") ActorRef counterActor) {
        this.counterActor = counterActor;
    }

    @GetMapping("/count")
    public CompletionStage<Integer> count() {
        // Send a simple “increment” message and expect an Integer reply
        return Patterns
            .ask(counterActor, "incr", Duration.ofSeconds(5))
            .thenApply(obj -> (Integer) obj);
    }
}
