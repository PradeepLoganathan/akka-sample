package com.pradeepl.spring_akka_app.actors;

import akka.actor.AbstractActor;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component("counterActor")
@Scope("prototype")  // ensure a new instance per actor
public class CounterActor extends AbstractActor {
    private int count = 0;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .matchAny(msg -> {
                // increment on every message and reply with the new value
                count++;
                getSender().tell(count, getSelf());
            })
            .build();
    }
}
