package com.spring.data.controller;

import com.spring.data.dto.UserDto;
import com.spring.data.entity.User;
import com.spring.data.event.RegistrationCompleteEvent;
import com.spring.data.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto userDto,
                             final HttpServletRequest request)
    {
        final User user = registrationService.register(userDto);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
    }
    private String applicationUrl(final HttpServletRequest request)
    {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    }
}
