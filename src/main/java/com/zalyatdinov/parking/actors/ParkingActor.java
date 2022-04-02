package com.zalyatdinov.parking.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import org.springframework.jms.core.JmsTemplate;


public class ParkingActor extends AbstractActor {
    private static JmsTemplate jmsTemplate = null;

    ParkingActor() {

    }

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

    public static Props props(JmsTemplate jmsTemplate) {
        ParkingActor.jmsTemplate = jmsTemplate;
        return Props.create(ParkingActor.class);
    }

}
