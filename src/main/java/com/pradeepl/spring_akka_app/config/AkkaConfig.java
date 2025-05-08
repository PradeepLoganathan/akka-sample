package com.pradeepl.spring_akka_app.config;

import com.pradeepl.spring_akka_app.spring.SpringExtension;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    public static final String HELLO_ACTOR_NAME = "helloActor";
    public static final String COUNTER_ACTOR_NAME = "counterActor";

    
    @Bean
    public ActorSystem actorSystem(ApplicationContext ctx) {
        Config config = ConfigFactory.load();
        ActorSystem system = ActorSystem.create("spring-akka-app", config);
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).initialize(ctx);
        return system;
    }

    @Bean
    public ActorRef helloActorRef(ActorSystem system) {
        return system.actorOf(
            SpringExtension.SPRING_EXTENSION_PROVIDER
            .get(system)
            .props(HELLO_ACTOR_NAME),
            HELLO_ACTOR_NAME
        );
    }

    @Bean("counterActorRef")
    public ActorRef counterActorRef(ActorSystem system) {
        return system.actorOf(
            SpringExtension.SPRING_EXTENSION_PROVIDER
            .get(system)
            .props(COUNTER_ACTOR_NAME),
            COUNTER_ACTOR_NAME
        );
    }

}
