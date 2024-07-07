package com.example.restaurant.menu;

import com.example.restaurant.Restaurant;
import com.example.restaurant.menu.dto.MenuDto;
import org.springframework.stereotype.Service;

@Service
public class MenuMapper {
    Menu dtoToEntity(MenuDto dto, Restaurant restaurant){
        Menu menu = new Menu();
        menu.setDateDinner(dto.dateDinner());
        menu.setSoup(dto.soup());
        menu.setSoupPrice(dto.soupPrice());
        menu.setMainCourse(dto.mainCourse());
        menu.setPriceMainCourse(dto.priceMainCourse());
        restaurant.getMenus().add(menu);
        return menu;
    }
    MenuDto entityToDto(Menu menu){
        return new MenuDto(menu.getDateDinner(),menu.getSoup(),menu.getSoupPrice(), menu.getMainCourse(), menu.getPriceMainCourse());
    }
}
