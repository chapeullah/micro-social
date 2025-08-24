package org.chapeullah.infrastructure.kafka;

import org.chapeullah.UserRegistered;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventsListeners {

    @KafkaListener(
            topics = "auth.events",
            groupId = "profile-service"
    )
    public void onUserRegistered(UserRegistered userRegistered) {
        System.out.println("Пользователь зарегистрирован :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) ");
    }
}
