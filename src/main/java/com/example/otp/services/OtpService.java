package com.example.otp.services;

import com.example.otp.models.Otp;

public interface OtpService {
    Otp requestCode(String email);

    void clearAllByPerson(Long id);

    Otp findByCode(String code);

    Otp findByIdPerson(Long id);

    Otp save(Otp otp);
}
