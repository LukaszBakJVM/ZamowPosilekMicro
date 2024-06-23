package com.example.school;

import com.example.school.address.SchoolAddress;
import com.example.school.restaurant.Restaurant;
import jakarta.persistence.*;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    private String schoolName;
    @OneToOne
    private SchoolAddress address;

    private String restaurantName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public SchoolAddress getAddress() {
        return address;
    }

    public void setAddress(SchoolAddress address) {
        this.address = address;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
