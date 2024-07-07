package com.example.school;

import com.example.school.address.SchoolAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String schoolName;
    private String uuid;
    @Email(message = "Wrong email format ")
    private String email;
    private String password;
    @OneToOne
    private SchoolAddress address;
    private long restaurantId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
