package com.masai.SpringBootApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ControllerClass {


    @GetMapping("/greet")
    public ResponseEntity<String> getGreeting(){

        return new ResponseEntity<>("hello", HttpStatus.OK);

    }

}
