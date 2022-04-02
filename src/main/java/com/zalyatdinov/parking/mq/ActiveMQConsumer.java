package com.zalyatdinov.parking.mq;

import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {
    @JmsListener(destination = "sample.queue", containerFactory = "jsaFactory")
    public void receiveQueue(CarDto car) {
        System.out.println("Received from queue: " + car);
    }

    @JmsListener(destination = "my_topic")
    public void receiveTopic_1(int message) {
        System.out.println("Received from topic: " + message);
    }

    @JmsListener(destination = "dispatcher", containerFactory = "jsaFactory")
    public void receiveTopic_2(ParkPlace park) {
        System.out.println("Received from topic: " + park);
    }

}
