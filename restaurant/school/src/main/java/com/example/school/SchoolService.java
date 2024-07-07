package com.example.school;

import com.example.school.address.AddressRepository;
import com.example.school.dto.SchoolId;
import com.example.school.dto.SchoolRegistration;
import com.example.school.dto.SchoolRegistrationResponse;
import com.example.school.exception.SchoolNotFoundException;
import com.example.school.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.UUID;

@Service
public class SchoolService {
    private final SchoolMapper mapper;
    private final SchoolRepository schoolRepository;
    private final AddressRepository addressRepository;
    private final JavaMailSender mailSender;
    private final RestTemplate restTemplate;
    private final LocalValidatorFactoryBean validation;
    @Value("${restaurantUrl}")
    private String restaurantUrl;


    public SchoolService(SchoolMapper mapper, SchoolRepository schoolRepository, AddressRepository addressRepository, JavaMailSender mailSender, RestTemplate restTemplate, LocalValidatorFactoryBean validation) {
        this.mapper = mapper;
        this.schoolRepository = schoolRepository;
        this.addressRepository = addressRepository;
        this.mailSender = mailSender;
        this.restTemplate = restTemplate;
        this.validation = validation;
    }

    SchoolRegistrationResponse newSchool(SchoolRegistration dto) {
        String uuid = UUID.randomUUID().toString();

        School school = mapper.dtoToEntity(dto, uuid);
        validationClient(school);
        addressRepository.save(school.getAddress());
        School save = schoolRepository.save(school);
        sendNotification(save.getEmail(), save.getUuid());


        return mapper.entityToDto(save);
    }

    SchoolId findSchoolId(String uuid) {
        return schoolRepository.findByUuid(uuid).map(mapper::schoolId).orElseThrow(() -> new SchoolNotFoundException("No school found with the given code " + uuid));

    }

    private void sendNotification(String email, String uuid) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Unique code");
        mail.setText(uuid);
        //  mailSender.send(mail);
    }

    private void validationClient(School school) {
        Set<ConstraintViolation<School>> violations = validation.validate(school);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation error occurred: ");
            for (ConstraintViolation<School> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }

            throw new ValidationException(errorMessage.toString());

        }
    }

    SchoolRegistrationResponse updateSchool(long schoolId, long restaurantId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException("School not found"));
        school.setRestaurantId(restaurantId);
        School save = schoolRepository.save(school);
        return mapper.entityToDto(save);
    }
    void updateSchoolRestaurantId(long schoolId,long restaurantId){
        School school = schoolRepository.findById(schoolId).orElseThrow();
        school.setRestaurantId(restaurantId);
        schoolRepository.save(school);

    }


}


