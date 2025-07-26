package com.codewitharal.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("sms")
@Primary
public class SmsNotificationService implements NotificationService {

    @Override
    public void sendNotification(String notification) {
        System.out.println("Sending SMS: " + notification);
    }
}
