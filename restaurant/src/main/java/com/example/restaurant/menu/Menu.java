package com.example.restaurant.menu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateDinner;
    private String soup;
    private BigDecimal soupPrice;
    private String mainCourse;
    private String priceMainCourse;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateDinner() {
        return dateDinner;
    }

    public void setDateDinner(LocalDate localDate) {
        this.dateDinner = localDate;
    }

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public BigDecimal getSoupPrice() {
        return soupPrice;
    }

    public void setSoupPrice(BigDecimal soupPrice) {
        this.soupPrice = soupPrice;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getPriceMainCourse() {
        return priceMainCourse;
    }

    public void setPriceMainCourse(String priceMainCourse) {
        this.priceMainCourse = priceMainCourse;
    }
}
