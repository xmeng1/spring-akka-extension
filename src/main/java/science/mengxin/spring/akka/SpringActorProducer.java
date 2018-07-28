package science.mengxin.spring.akka;

/**
 * <p>Date:    26/07/18
 *
 * @author mengxin
 * @version 1.0
 */

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

public class SpringActorProducer implements IndirectActorProducer {

    private ApplicationContext applicationContext;

    private String beanActorName;

    private Object[] args;

    public SpringActorProducer(ApplicationContext applicationContext, String beanActorName) {
        this.applicationContext = applicationContext;
        this.beanActorName = beanActorName;
        this.args = null;
    }

    public SpringActorProducer(ApplicationContext applicationContext, String beanActorName, Object[] args) {
        this.applicationContext = applicationContext;
        this.beanActorName = beanActorName;
        this.args = args;
    }

    @Override
    public Actor produce() {
        if (args == null) {
            return (Actor) applicationContext.getBean(beanActorName);
        } else {
            return (Actor) applicationContext.getBean(beanActorName, args);
        }
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(beanActorName);
    }

}
