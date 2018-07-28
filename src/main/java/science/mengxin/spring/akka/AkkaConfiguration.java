package science.mengxin.spring.akka;

import static science.mengxin.spring.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * <p>Date:    28/07/18
 *
 * @author mengxin
 * @version 1.0
 */
//@Configuration
//@ComponentScan
public class AkkaConfiguration {

    public static final String ACTOR_SYSTEM = "default-actorSystem";
    public static final String WORK_ACTOR = "default-work-actor";


    private ApplicationContext applicationContext;

    @Autowired
    public AkkaConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private ActorSystem actorSystem;

    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    @Bean(name = ACTOR_SYSTEM)
    public ActorSystem actorSystem() {
        actorSystem = ActorSystem.create(ACTOR_SYSTEM);
        SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }
//
//    @Bean(name = WORK_ACTOR)
//    @DependsOn({ACTOR_SYSTEM})
//    public ActorRef workActor() {
//        return actorSystem
//                .actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("workActor"), WORK_ACTOR);
//    }
}
