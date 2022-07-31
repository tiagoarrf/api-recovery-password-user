package com.example.otp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.otp.models.Person;
import com.example.otp.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/{email}")
    public ResponseEntity<Person> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(personService.findByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping("add")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.ok(personService.save(person));
    }

    @PutMapping("edit")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        return ResponseEntity.ok(personService.update(person));
    }

    @PostMapping("reset-password")
    public ResponseEntity<Person> resetPassword(@RequestBody @RequestParam(value = "otp") String otp,
            @RequestParam(value = "newPassword") String newPassword) {
        return ResponseEntity.ok(personService.newPassword(otp, newPassword));
    }

}
