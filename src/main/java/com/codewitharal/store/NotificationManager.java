package com.codewitharal.store;

import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private NotificationService notificationService;

    public NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(String notification, String recipientEmail) {
        notificationService.sendNotification(notification, recipientEmail);
    }
}
