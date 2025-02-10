package com.larry.pojo.notification;

public class SMSNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("SMS Notification sent to " + recipient + ": " + message);
    }
}
