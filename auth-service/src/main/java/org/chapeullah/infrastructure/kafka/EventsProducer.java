package org.chapeullah.infrastructure.kafka;

import org.chapeullah.UserRegistered;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;

@Service
public class EventsProducer {
    private final KafkaTemplate<String, UserRegistered> kafkaTemplate;
    private final String AUTH_EVENTS_TOPIC = "auth.events";

    EventsProducer(KafkaTemplate<String, UserRegistered> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void userRegistered(int userId) {
        UserRegistered userRegistered = new UserRegistered(userId, Instant.now());
        kafkaTemplate.send(AUTH_EVENTS_TOPIC, String.valueOf(userId), userRegistered);
    }
}
