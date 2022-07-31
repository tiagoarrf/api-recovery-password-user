package com.example.otp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.otp.models.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    List<Otp> findAll();

    Otp findByCode(String code);

    @Query(value = "SELECT * FROM Otp WHERE person_id = :id ;", nativeQuery = true)
    Otp findByIdPerson(Long id);

    @Modifying
    @Query("delete from Otp c where c.person.id=:id")
    void clearAllByPerson(Long id);

}
