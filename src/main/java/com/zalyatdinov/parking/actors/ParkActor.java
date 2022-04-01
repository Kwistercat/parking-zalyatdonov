package com.zalyatdinov.parking.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;


@RequiredArgsConstructor
public class ParkActor extends AbstractActor {
    private final JmsTemplate jmsTemplate;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ParkPlace.class, this::sendPark)
                .build();
    }

    private void sendPark(ParkPlace parkPlace) {
        System.out.println("Received ParkPlace: " + parkPlace);
        if (parkPlace.getParkStatus() == ParkStatus.BUSY && parkPlace.getPayStatus() == PayStatus.NOT_PAID) {
            jmsTemplate.convertAndSend("dispatcher", parkPlace);
        }
    }

    public static Props props() {
        return Props.create(ParkActor.class);
    }
}

