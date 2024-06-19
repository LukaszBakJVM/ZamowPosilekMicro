package com.example.school.address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<SchoolAddress,Long> {
    SchoolAddress save(SchoolAddress schoolAddress);
}
