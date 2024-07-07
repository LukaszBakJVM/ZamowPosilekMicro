package com.example.school.dto;

public record SchoolRegistrationResponse(String schoolName, String city, String zipCode, String street, int localNumber,
                                         String email) {
}
