package com.pradeepl.spring_akka_app.spring;

import akka.actor.*;
import org.springframework.context.ApplicationContext;

public class SpringExtension 
    extends AbstractExtensionId<SpringExtension.SpringExt>
    implements ExtensionIdProvider {

    public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    @Override
    public ExtensionId<? extends Extension> lookup() {
        return SPRING_EXTENSION_PROVIDER;
    }

    public static class SpringExt implements Extension {
        private volatile ApplicationContext applicationContext;
        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }
        public Props props(String actorBeanName) {
            return Props.create(
                SpringActorProducer.class,
                applicationContext, actorBeanName
            );
        }
    }
}
