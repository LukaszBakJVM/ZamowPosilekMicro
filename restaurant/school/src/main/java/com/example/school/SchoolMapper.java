package com.example.school;

import com.example.school.address.SchoolAddress;
import com.example.school.dto.SchoolId;
import com.example.school.dto.SchoolLogin;
import com.example.school.dto.SchoolRegistration;
import com.example.school.dto.SchoolRegistrationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    private final PasswordEncoder passwordEncoder;

    public SchoolMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    School dtoToEntity(SchoolRegistration dto, String uuid) {
        School school = new School();
        SchoolAddress address = new SchoolAddress();
        school.setSchoolName(dto.schoolName());
        school.setUuid(uuid);
        school.setEmail(dto.email());
        String password = passwordEncoder.encode(dto.password());
        school.setPassword(password);
        address.setCity(dto.city());
        address.setZipCode(dto.zipCode());
        address.setStreet(dto.street());
        address.setLocalNumber(dto.localNumber());
        school.setAddress(address);
        return school;
    }

    SchoolRegistrationResponse entityToDto(School school) {
        return new SchoolRegistrationResponse(school.getSchoolName(), school.getAddress().getCity(), school.getAddress().getZipCode(), school.getAddress().getStreet(), school.getAddress().getLocalNumber(), school.getEmail());
    }

    SchoolId schoolId(School school) {
        return new SchoolId(school.getId());
    }
    SchoolLogin login(School school){
        return new SchoolLogin(school.getEmail(),school.getPassword());
    }
}
