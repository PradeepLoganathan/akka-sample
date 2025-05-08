package com.pradeepl.spring_akka_app.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;

import akka.event.Logging;
import akka.event.LoggingAdapter;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component("helloActor")
@Scope("prototype")  // important: actors must be prototype-scoped
public class HelloActor extends AbstractActor {
    // Immutable message definitions
    public static class GreetCommand {
        public final String name;
        public GreetCommand(String name) {
            this.name = name;
        }
    }
    
    // Logger
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    // Actor factory method
    // This is the method that will be called to create a new instance of this actor
    public static Props props() {
        return Props.create(HelloActor.class);
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(GreetCommand.class, msg -> {
                // Reply with the greeting string
                getSender().tell("Hello, " + msg.name + "!", getSelf());
            })
            .matchAny(msg -> {
                // Optional: Handle unexpected messages
                log.warning("Received unknown message: {}", msg);
                unhandled(msg);
            })
            .build();
    }
}
