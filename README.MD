# Introduction

AKKA + Spring

idea from : [Introduction to Spring with Akka](http://www.baeldung.com/akka-with-spring)

# Usage

## 1. Create Spring Boot project

Create Spring Boot project from website or Intellij.

## 2. add the dependency to the maven

```xml
<dependency>
    <groupId>mengxin.science.spring</groupId>
    <artifactId>spring-akka-extension</artifactId>
    <version>0.1.0</version>
</dependency>
```
3. Create Spring Configuration

```java
@Configuration
@ComponentScan
public class AkkaConfig extends AkkaConfiguration {
    public AkkaConfig(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
```

The system actor will be configured automatically.



4. Configure other actor

add Actor class

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginActor extends UntypedActor {

    @Autowired
    private LoginService loginService;

    @Override
    public void onReceive(Object message) {
        if (message instanceof String) {
            // do something
        }else {
            unhandled(message);
        }

    }
}

```

If you want to configure your actor, add the method in the Configuration class as follows:

```java
@Configuration
@ComponentScan
public class AkkaConfig extends AkkaConfiguration {

    public static final String LOGIN_ACTOR = "login-actor";

    public AkkaConfig(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Bean(name = LOGIN_ACTOR)
    @DependsOn({"default-actorSystem"})
    public ActorRef loginActor() {
        return this.getActorSystem()
                .actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER
                        .get(this.getActorSystem())
                        .props("loginActor"), LOGIN_ACTOR);
    }
}
``` 

5. User actor with AutoWire

```java
public class AkkaResource {

    private ActorRef loginActor;
    
    public AkkaResource(@Qualifier(AkkaConfig.LOGIN_ACTOR) ActorRef loginActor) {
        this.loginActor = loginActor;
    }
}
```

# Demo

please check this repo with branch to test:

https://github.com/xmeng1/spring-web-tutorial/tree/feature/akka-async-request