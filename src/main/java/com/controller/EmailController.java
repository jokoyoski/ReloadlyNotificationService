package com.controller;

import com.main.contract.IEmailService;
import com.main.model.ServerResponse;
import com.main.model.request.SendEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class EmailController {

    @Autowired
    private IEmailService _emailService;


    @PostMapping("/send")
    public ResponseEntity<?> AddUser(@RequestBody SendEmailRequest sendEmailRequest) throws Exception {

       _emailService.sendEmail(sendEmailRequest.getEmail(),sendEmailRequest.getName(),sendEmailRequest.getAmount());
        return new ResponseEntity<Object>("Email Sent Successfully", HttpStatus.CREATED);
    }

}
