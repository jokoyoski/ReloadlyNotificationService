package com.main.contract;

import org.springframework.stereotype.Component;
@Component
public interface IEmailService {



        void sendEmail(String toEmail, String firstName, double Amount);


}
