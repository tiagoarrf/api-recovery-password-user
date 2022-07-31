package com.example.otp.services.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.otp.exceptions.GenericUsualException;
import com.example.otp.models.Otp;
import com.example.otp.models.Person;
import com.example.otp.repositories.OtpRepository;
import com.example.otp.repositories.PersonRepository;
import com.example.otp.services.PersonService;
import com.example.otp.utils.ValidExpirationOTP;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public Person save(Person person) {
        if (findByEmail(person.getEmail()) == null) {
            person.setPassword(encoder.encode(person.getPassword()));
            return personRepository.save(person);
        } else {
            throw new GenericUsualException("Error: email already registered.");
        }
    }

    @Override
    @Transactional
    public Person update(Person person) {
        if (findById(person.getId()) != null) {
            return personRepository.save(person);
        } else {
            throw new GenericUsualException("Error for update data person");
        }
    }

    @Override
    @Transactional
    public Person newPassword(String otp, String newPassword) {
        Otp otpRepo = otpRepository.findByCode(otp);
        Integer tentativas = otpRepo.getAttemps();
        tentativas--;
        otpRepo.setAttemps(tentativas);
        otpRepository.save(otpRepo);
        Person person = personRepository.findById(otpRepo.getPerson().getId()).orElse(null);
        if (person != null) {
            Date start_date = otpRepo.getDataOtp();
            Date end_date = new java.util.Date(System.currentTimeMillis());
            Long expDate = ValidExpirationOTP.findDifference(start_date, end_date);
            if (otpRepo.getAttemps() > 0 && expDate < 50) {
                person.setPassword(encoder.encode(newPassword));
                otpRepository.clearAllByPerson(person.getId());
                return personRepository.save(person);
            }
            throw new GenericUsualException("Error in validation for email person");
        } else {
            throw new GenericUsualException("Code invalid or expired! Retry");
        }
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

}
