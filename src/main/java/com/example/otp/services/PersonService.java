package com.example.otp.services;

import java.util.List;

import com.example.otp.models.Person;

public interface PersonService {
    Person findByEmail(String email);

    List<Person> findAll();

    Person save(Person person);

    Person update(Person person);

    Person newPassword(String otp, String newPassword);

    Person findById(Long id);

}
