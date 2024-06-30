package com.example.school;

import com.example.school.address.SchoolAddress;
import com.example.school.dto.SchoolId;
import com.example.school.dto.SchoolRegistration;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    School dtoToEntity(SchoolRegistration dto, String uuid) {
        School school = new School();
        SchoolAddress address = new SchoolAddress();
        school.setSchoolName(dto.schoolName());
        school.setUuid(uuid);
        school.setEmail(dto.email());
        address.setCity(dto.city());
        address.setZipCode(dto.zipCode());
        address.setStreet(dto.street());
        address.setLocalNumber(dto.localNumber());
        school.setAddress(address);
        return school;
    }

    SchoolRegistration entityToDto(School school) {
        return new SchoolRegistration(school.getSchoolName(), school.getAddress().getCity(), school.getAddress().getZipCode(), school.getAddress().getStreet(), school.getAddress().getLocalNumber(), school.getEmail());
    }

    SchoolId schoolId(School school) {
        return new SchoolId(school.getId());
    }
}
