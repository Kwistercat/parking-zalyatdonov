package com.zalyatdinov.parking.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@RequiredArgsConstructor
public class ActorConfig {
    public final JmsTemplate jmsTemplate;

    @Bean
    public ActorRef parkingActor() {
        ActorSystem akkaSystem = ActorSystem.create("mySystem");
        return akkaSystem.actorOf(ParkingActor.props(jmsTemplate), "parkingActor");
    }
}
