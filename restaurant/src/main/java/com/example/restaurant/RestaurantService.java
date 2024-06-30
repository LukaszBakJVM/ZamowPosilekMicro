package com.example.restaurant;

import com.example.restaurant.address.AddressRepository;
import com.example.restaurant.dto.RestaurantRegistrationDto;
import com.example.restaurant.exception.SchoolNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
        Long schoolIdByUuid = findSchoolIdByUuid(schoolUrl, uuid);
        Restaurant restaurant = mapper.registrationDtoToEntity(dto, schoolIdByUuid);
        addressRepository.save(restaurant.getAddress());
        Restaurant save = restaurantRepository.save(restaurant);
        return mapper.entityToRegistrationDto(save);

    }

    private Long findSchoolIdByUuid(String schoolUrl, String uuid) {
        String url = UriComponentsBuilder.fromHttpUrl(schoolUrl).path("/school/findSchoolId").queryParam("schoolUuid", uuid).toUriString();

        return restTemplate.getForObject(url, Long.class);
    }
    RestaurantRegistrationDto findRestaurantBySchoolId(long id){
        Restaurant restaurant = restaurantRepository.findBySchoolId(id).orElseThrow(() -> new SchoolNotFoundException("No restaurant assigned to school with id " + id));
        return mapper.entityToRegistrationDto(restaurant);
    }
}
