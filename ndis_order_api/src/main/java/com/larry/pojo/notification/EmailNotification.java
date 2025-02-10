package com.larry.pojo.notification;

public class EmailNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Email Notification sent to " + recipient + ": " + message);
    }
}
