package com.example.school;

import com.example.school.dto.SchoolLogin;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolLoginService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolLoginService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }
   public Optional<SchoolLogin>login(String email){
        return schoolRepository.findByEmail(email).map(schoolMapper::login);
    }
}
