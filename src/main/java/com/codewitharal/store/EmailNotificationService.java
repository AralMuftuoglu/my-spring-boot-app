package com.codewitharal.store;

import org.springframework.stereotype.Service;

@Service("email")
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String notification) {
        System.out.println("Sending email: " + notification);
    }

}
