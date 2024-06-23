package com.example.school;

import com.example.school.address.SchoolAddress;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    School dtoToEntity(SchoolDto dto, String restaurantName) {
        School school = new School();
        SchoolAddress address = new SchoolAddress();
        school.setSchoolName(dto.schoolName());
        school.setRestaurantName(restaurantName);
        address.setCity(dto.city());
        address.setZipCode(dto.zipCode());
        address.setStreet(dto.street());
        address.setLocalNumber(dto.localNumber());
        school.setAddress(address);
        return school;
    }

    SchoolDto entityToDto(School school) {
        return new SchoolDto(school.getSchoolName(), school.getAddress().getCity(), school.getAddress().getZipCode(), school.getAddress().getStreet(), school.getAddress().getLocalNumber(), school.getRestaurantName());
    }
}
