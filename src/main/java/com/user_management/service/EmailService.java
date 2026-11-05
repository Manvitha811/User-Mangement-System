package com.user_management.service;

public interface EmailService {
    public boolean sendEmail(String to,String subject,String body);
}
