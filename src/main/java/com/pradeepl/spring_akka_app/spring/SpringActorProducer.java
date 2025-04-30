package com.pradeepl.spring_akka_app.spring;

import akka.actor.IndirectActorProducer;
import akka.actor.Actor;
import org.springframework.context.ApplicationContext;

public class SpringActorProducer implements IndirectActorProducer {
    private final ApplicationContext ctx;
    private final String actorBeanName;

    public SpringActorProducer(ApplicationContext ctx, String actorBeanName) {
        this.ctx = ctx;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) ctx.getBean(actorBeanName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) ctx.getType(actorBeanName);
    }
}
