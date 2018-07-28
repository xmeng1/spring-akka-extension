package science.mengxin.spring.akka;

import akka.actor.UntypedActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <p>Date:    27/07/18
 *
 * @author mengxin
 * @version 1.0
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkActor extends UntypedActor {

    @Override
    public void onReceive(Object message) {
        if (message instanceof String){
            getSender().tell(message, getSelf());
        } else {
            unhandled(message);
        }
    }
}

