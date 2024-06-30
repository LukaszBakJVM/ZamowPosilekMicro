package com.example.restaurant;

import com.example.restaurant.address.AddressRepository;
import com.example.restaurant.dto.RestaurantRegistrationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final RestaurantMapper mapper;
    private final RestTemplate restTemplate;
    @Value("${schoolUrl}")
    private String schoolUrl;

    public RestaurantService(RestaurantRepository restaurantRepository, AddressRepository addressRepository, RestaurantMapper mapper, RestTemplate restTemplate) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
        this.restTemplate = restTemplate;
    }

    RestaurantRegistrationDto newRestaurant(RestaurantRegistrationDto dto, String uuid) {
        Long schoolId = restTemplate.getForObject(schoolUrl + "school/findSchoolId?schoolUuid=" + uuid, Long.class);
        Restaurant restaurant = mapper.registrationDtoToEntity(dto, schoolId);
        addressRepository.save(restaurant.getAddress());
        Restaurant save = restaurantRepository.save(restaurant);
        return mapper.entityToRegistrationDto(save);

    }
}
