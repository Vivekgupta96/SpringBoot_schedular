package com.masai.SpringBootApp.Service.Impl;

import com.masai.SpringBootApp.Service.MailService;
import com.masai.SpringBootApp.exception.MailSenderException;
import com.masai.SpringBootApp.model.MailStructure;
import com.masai.SpringBootApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String mailSendFrom;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Scheduled(fixedDelay = 180000)
    public void sendMail() {

        try{

            MailStructure mailStructure=new MailStructure();
            mailStructure.setMailSubject("message using the mail schedular");
            mailStructure.setMailBody("email fromm the mail scheduler at every 3 minute");

            SimpleMailMessage email=new SimpleMailMessage();
            email.setFrom(mailSendFrom);
            email.setSubject(mailStructure.getMailSubject());
            email.setText(mailStructure.getMailBody()+" Total Live USer "+userRepository.getLiveUserInDataBase());
            email.setTo("vivekg0691@gmail.com");
            email.setSentDate(new Date());


            mailSender.send(email);
            log.info("mail send succesffuly count :");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new MailSenderException(
                    "Failed to sending the email to user with Id :"+"guptavivek1496@gmail.com");

        }

    }
}
