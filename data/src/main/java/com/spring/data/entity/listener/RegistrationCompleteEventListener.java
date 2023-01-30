package com.spring.data.entity.listener;

import com.spring.data.entity.User;
import com.spring.data.event.RegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
            final User user = event.getUser();
            final String token = UUID.randomUUID().toString();
            //save user and
            final String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;
            log.info("Click the link to verify your account {}" + url);
    }
}
