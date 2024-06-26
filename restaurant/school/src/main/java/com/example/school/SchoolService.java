package com.example.school;

import com.example.school.Dto.SchoolRegistration;
import com.example.school.address.AddressRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        School school = mapper.dtoToEntity(dto);
        addressRepository.save(school.getAddress());
        School save = schoolRepository.save(school);
        return mapper.entityToDto(save);


    }
}
