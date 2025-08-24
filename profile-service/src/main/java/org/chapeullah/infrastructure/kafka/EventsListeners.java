package org.chapeullah.infrastructure.kafka;

import org.chapeullah.UserRegistered;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
        topics = "auth.events",
        groupId = "profile-service",
        properties = {
                "key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
                "value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer",
                "spring.json.trusted.packages=org.chapeullah",
                "spring.json.value.default.type=org.chapeullah.UserRegistered"
        }
)
public class EventsListeners {

    @KafkaHandler
    public void onUserRegistered(UserRegistered userRegistered) {
        System.out.println("Пользователь зарегистрирован :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) ");
    }
}
