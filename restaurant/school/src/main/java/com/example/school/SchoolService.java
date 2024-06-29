package com.example.school;

import com.example.school.dto.SchoolId;
import com.example.school.dto.SchoolRegistration;
import com.example.school.address.AddressRepository;
import com.example.school.exception.SchoolNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class SchoolService {
    private final SchoolMapper mapper;
    private final SchoolRepository schoolRepository;
    private final AddressRepository addressRepository;
    private final RestTemplate restTemplate;
    @Value("${restaurantUrl}")
    private String restaurantUrl;


    public SchoolService(SchoolMapper mapper, SchoolRepository schoolRepository, AddressRepository addressRepository, RestTemplate restTemplate) {
        this.mapper = mapper;
        this.schoolRepository = schoolRepository;
        this.addressRepository = addressRepository;
        this.restTemplate = restTemplate;
    }

    SchoolRegistration newSchool(SchoolRegistration dto) {
       String uuid = UUID.randomUUID().toString();

        School school = mapper.dtoToEntity(dto,uuid);
        addressRepository.save(school.getAddress());
        School save = schoolRepository.save(school);
        return mapper.entityToDto(save);
    }
    SchoolId findSchoolId(String uuid){
        return schoolRepository.findByUuid(uuid).map(mapper::schoolId).orElseThrow(()->new SchoolNotFoundException("No school found with the given code "+uuid));

    }
}
