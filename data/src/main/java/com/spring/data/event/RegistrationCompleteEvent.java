package com.spring.data.event;

import com.spring.data.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;
    public RegistrationCompleteEvent(final User user, final String applicationUrl){
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
