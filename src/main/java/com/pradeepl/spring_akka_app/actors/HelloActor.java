package com.pradeepl.spring_akka_app.actors;

import akka.actor.AbstractActor;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component("helloActor")
@Scope("prototype")  // important: actors must be prototype-scoped
public class HelloActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(String.class, name -> {
                getSender().tell("Hello, " + name + "!", getSelf());
            })
            .build();
    }
}
