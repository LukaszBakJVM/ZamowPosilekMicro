package com.example.restaurant.menu;

import com.example.restaurant.Restaurant;
import com.example.restaurant.RestaurantRepository;
import com.example.restaurant.menu.dto.MenuDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuMapper mapper;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository, MenuMapper mapper) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }
    MenuDto addMenuToRestaurant(MenuDto dto,long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        Menu menu = mapper.dtoToEntity(dto, restaurant);
        Menu save = menuRepository.save(menu);
        return mapper.entityToDto(save);
    }
    List<MenuDto>findMenuByRestaurantId(long id){
        return menuRepository.findMenuByRestaurantId(id).stream().map(mapper::entityToDto).toList();
    }
}
