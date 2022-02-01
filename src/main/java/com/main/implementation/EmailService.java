package com.main.implementation;


import com.main.contract.IEmailService;
import com.repository.factories.AppSettingQuery;
import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements IEmailService {

    @Autowired
    private AppSettingQuery appSettingQuery;

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    private  void sendEmailGrid(String subject, String body, String fromEmail, String toEmail){

        String API_KEY = appSettingQuery.getSetting("SendGrid_Email_API_KEY").getValue();

        Email from = new Email(fromEmail);

        Email to = new Email(toEmail);

        Content content = new Content("text/html", body);

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());

        } catch (Exception ex) {
            logger.error(String.format("Error occurred %s", ex.getMessage()));
        }
    }

    public void sendResetEmail(String toEmail, String firstName, String code) {

        String ADMIN_EMAIL = appSettingQuery.getSetting("ADMIN_EMAIL").getValue();

        String subject = "Reset to Password";

        String body = String.format("<html>Hi %s, <br/><br/> Please use code to reset password <br/> <br/> Code: %s</html>", firstName, code);

        try{
            sendEmailGrid(subject, body, "bomana.ogoni@hotmail.com", toEmail);
        }
        catch (Exception ex){
            logger.error(String.format("Error occurred {0}", ex.getMessage()));
        }

    }


    @Override
    public void sendEmail(String toEmail, String firstName, double Amount) {
        String ADMIN_EMAIL = appSettingQuery.getSetting("ADMIN_EMAIL").getValue();

        String subject = "Deposit Mail";

        String body = String.format("<html>Hi %s, <br/> Congratulations your  have just credited your account with  %s<br/><br/>", firstName,Amount);

        try{
            sendEmailGrid(subject, body, ADMIN_EMAIL, toEmail);
        }
        catch (Exception ex){
            logger.error(String.format("Error occurred %s", ex));
        }
    }
}


