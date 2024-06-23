package com.example.school;

import com.example.school.address.AddressRepository;
import com.example.school.restaurant.Restaurant;
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

    SchoolDto newSchool(SchoolDto dto) {
        String restaurantName = restTemplate.getForObject(restaurantUrl + dto.restaurantName(), Restaurant.class).restaurantName();
        School school = mapper.dtoToEntity(dto, restaurantName);
        addressRepository.save(school.getAddress());
        School save = schoolRepository.save(school);
        return mapper.entityToDto(save);


    }
}
