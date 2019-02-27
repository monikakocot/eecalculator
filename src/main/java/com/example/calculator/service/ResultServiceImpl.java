package com.example.calculator.service;


import com.example.calculator.model.Compressor;
import com.example.calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultsSevice{

    @Autowired
    MailService mailService;

    @Override
    public void sendResults(long result, User user) {
        mailService.sendEmail(result,user);

    }
}
