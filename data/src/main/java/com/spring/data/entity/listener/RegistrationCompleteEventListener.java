package com.spring.data.entity.listener;

import com.spring.data.event.RegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        System.out.println("PHUC " + event.getApplicationUrl());
    }
}
