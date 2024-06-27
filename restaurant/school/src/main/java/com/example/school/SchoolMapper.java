package com.example.school;

import com.example.school.Dto.SchoolId;
import com.example.school.Dto.SchoolRegistration;
import com.example.school.address.SchoolAddress;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    School dtoToEntity(SchoolRegistration dto,String uuid) {
        School school = new School();
        SchoolAddress address = new SchoolAddress();
        school.setSchoolName(dto.schoolName());
        school.setUuid(uuid);
        address.setCity(dto.city());
        address.setZipCode(dto.zipCode());
        address.setStreet(dto.street());
        address.setLocalNumber(dto.localNumber());
        school.setAddress(address);
        return school;
    }

    SchoolRegistration entityToDto(School school) {
        return new SchoolRegistration(school.getSchoolName(), school.getAddress().getCity(), school.getAddress().getZipCode(), school.getAddress().getStreet(), school.getAddress().getLocalNumber());
    }
    SchoolId schoolId(School school){
        return new SchoolId(school.getId());
    }
}
