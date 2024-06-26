package com.example.school;

import com.example.school.Dto.SchoolRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
