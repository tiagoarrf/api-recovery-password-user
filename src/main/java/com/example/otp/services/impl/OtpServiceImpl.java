package com.example.otp.services.impl;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.otp.exceptions.GenericUsualException;
import com.example.otp.models.Otp;
import com.example.otp.models.Person;
import com.example.otp.repositories.OtpRepository;
import com.example.otp.repositories.PersonRepository;
import com.example.otp.services.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    @Transactional
    public Otp requestCode(String email) {
        Person person = personRepository.findByEmail(email);
        if (person != null) {
            otpRepository.clearAllByPerson(person.getId());
            Random random = new Random();
            String otp = String.valueOf(100000 + random.nextInt(900000));
            Otp objOtp = new Otp();
            objOtp.setCode(otp);
            objOtp.setAttemps(2);
            objOtp.setPerson(person);
            sendEmail(email, otp);
            return save(objOtp);
        } else {
            throw new GenericUsualException("User not found!");
        }
    }

    public void sendEmail(String emailTo, String otp) {
        String emailFrom = "<email-from-defaul>";
        String content = "Hello! You code is: " + otp;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailTo);
            message.setSubject("Reset Password");
            message.setText(content);
            emailSender.send(message);
        } catch (MailException e) {
            throw new GenericUsualException("Error in send code OTP");
        }
    }

    @Override
    @Transactional
    public void clearAllByPerson(Long id) {
        try {
            otpRepository.clearAllByPerson(id);
        } catch (Exception e) {
            throw new GenericUsualException("Error detetion code user");
        }
    }

    @Override
    @Transactional
    public Otp save(Otp otp) {
        return otpRepository.save(otp);
    }

    @Override
    public Otp findByCode(String code) {
        return otpRepository.findByCode(code);
    }

    @Override
    public Otp findByIdPerson(Long id) {
        return otpRepository.findByIdPerson(id);
    }

}
