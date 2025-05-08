package com.pradeepl.spring_akka_app.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component("counterActor")
@Scope("prototype")  // ensure a new instance per actor
public class CounterActor extends AbstractActor {

    // Immutable message definitions
    public static class IncrementCommand {}
    public static class GetCount {}
    public static class CurrentCount {
        public final int count;
        public CurrentCount(int count) { this.count = count; }
    }

     // Internal state
    private int count = 0;

    // Logger
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    // Actor factory method
    // This is the method that will be called to create a new instance of this actor
    public static Props props() {
        return Props.create(CounterActor.class);
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(IncrementCommand.class, msg -> {
                count++;
                log.info("Counter incremented to: {}", count);
            })
            .match(GetCount.class, msg -> {
                getSender().tell(new CurrentCount(count), getSelf());
            })
            .matchAny(msg -> {
                log.warning("Received unknown message: {}", msg);
                unhandled(msg);
            })
            .build();
    }
}


