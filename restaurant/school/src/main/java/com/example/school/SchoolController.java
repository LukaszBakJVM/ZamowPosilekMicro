package com.example.school;

import com.example.school.dto.SchoolRegistration;
import com.example.school.dto.SchoolRegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/newSchool")
    ResponseEntity<SchoolRegistrationResponse> addNewSchool(@RequestBody SchoolRegistration schoolRegistration) {
        SchoolRegistrationResponse schoolRegistrationResponse = schoolService.newSchool(schoolRegistration);
        URI savedSchoolUri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(schoolRegistrationResponse).toUri();
        return ResponseEntity.created(savedSchoolUri).body(schoolRegistrationResponse);
    }

    @GetMapping("/findSchoolId")
    long id(@RequestParam String schoolUuid) {
        return schoolService.findSchoolId(schoolUuid).id();
    }

@GetMapping("/{schoolId}")
void setRestaurantId(@PathVariable long schoolId,@RequestParam long restaurantId) {
        schoolService.updateSchoolRestaurantId(schoolId,restaurantId);
}


}
