package com.example.restaurant;

import com.example.restaurant.address.RestaurantAddress;
import com.example.restaurant.dto.RestaurantRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapper {

    Restaurant registrationDtoToEntity(RestaurantRegistrationDto registration, long schoolId) {
        Restaurant restaurant = new Restaurant();
        RestaurantAddress address = new RestaurantAddress();
        restaurant.setRestaurantName(registration.restaurantName());
        restaurant.setBankAccount(registration.bankAccount());

        address.setStreet(registration.street());
        address.setNumber(registration.number());
        restaurant.setAddress(address);
        restaurant.setSchoolId(schoolId);
        return restaurant;
    }

    RestaurantRegistrationDto entityToRegistrationDto(Restaurant restaurant) {
        return new RestaurantRegistrationDto(restaurant.getRestaurantName(), restaurant.getAddress().getStreet(), restaurant.getAddress().getNumber(), restaurant.getSchoolId(),restaurant.getBankAccount());
    }


}
