package com.example.restaurant.menu.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MenuDto(LocalDate dateDinner, String soup, BigDecimal soupPrice, String mainCourse,
                      String priceMainCourse) {
}
