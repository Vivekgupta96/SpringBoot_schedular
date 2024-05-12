package com.masai.SpringBootApp.exception;



public class MailSenderException  extends RuntimeException{
    public MailSenderException(String msg){
        super(msg);
    }

}
