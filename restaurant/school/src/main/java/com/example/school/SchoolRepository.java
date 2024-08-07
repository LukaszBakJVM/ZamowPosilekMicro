package com.example.school;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School,Long> {
    List<School> findByAddress_City(String city);
    School save(School school);
    Optional<School>findByUuid(String uuid);
    Optional<School>findById(long id);
    Optional<School>findByEmail(String email);
}
