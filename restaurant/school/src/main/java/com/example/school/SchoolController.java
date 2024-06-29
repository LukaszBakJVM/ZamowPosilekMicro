package com.example.school;

import com.example.school.dto.SchoolRegistration;
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
    ResponseEntity<SchoolRegistration>addNewSchool(@RequestBody SchoolRegistration schoolRegistration){
        SchoolRegistration saved = schoolService.newSchool(schoolRegistration);
        URI savedCompanyUri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(saved.schoolName()).toUri();
        return ResponseEntity.created(savedCompanyUri).body(saved);
    }
    @GetMapping("/findSchoolId")
        long id(@RequestParam String schoolUuid){
        return schoolService.findSchoolId(schoolUuid).id();
        }
}
