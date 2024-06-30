package com.example.restaurant;

import com.example.restaurant.dto.RestaurantRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    ResponseEntity<RestaurantRegistrationDto> newRestaurant(@RequestBody RestaurantRegistrationDto dto, @RequestParam String uuid) {
        RestaurantRegistrationDto restaurantRegistrationDto = restaurantService.newRestaurant(dto, uuid);
        URI savedRestaurantUri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(restaurantRegistrationDto.restaurantName()).toUri();
        return ResponseEntity.created(savedRestaurantUri).body(restaurantRegistrationDto);

    }
    @GetMapping("/school/{id}")
    RestaurantRegistrationDto findBySchoolId(@PathVariable long id){
        return restaurantService.findRestaurantBySchoolId(id);
    }
}
