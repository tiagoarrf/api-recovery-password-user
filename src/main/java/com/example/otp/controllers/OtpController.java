package com.example.otp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.otp.models.Otp;
import com.example.otp.services.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("request-new-password")
    public ResponseEntity<Otp> requestCode(@RequestBody @RequestParam(value = "email") String email) {
        return ResponseEntity.ok(otpService.requestCode(email));
    }
}
