package com.example.restaurant.menu;

import com.example.restaurant.menu.dto.MenuDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }
    @PostMapping("/addmenu")
    ResponseEntity<MenuDto> addNewMenu(@RequestBody MenuDto dto, @RequestParam long id){
        MenuDto menuDto = service.addMenuToRestaurant(dto, id);
        URI savedMenuUri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(menuDto).toUri();
        return ResponseEntity.created(savedMenuUri).body(menuDto);

    }
}
