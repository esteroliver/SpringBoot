package com.developer.medvoll.person.doctor;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("")
    public String helloWorld(){
        return "Hello world!";
    }

    @PostMapping("")
    public ResponseEntity<Doctor> create(@RequestBody @Valid DoctorDto new_doctor_dto){
        Doctor new_doctor = new Doctor();
        BeanUtils.copyProperties(new_doctor_dto, new_doctor);
        Doctor doctor_response = doctorService.createDoctor(new_doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor_response);
    }
}
