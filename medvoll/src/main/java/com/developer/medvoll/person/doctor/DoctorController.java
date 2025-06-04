package com.developer.medvoll.person.doctor;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("medicos")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello world!";
    }

    @PostMapping("")
    public ResponseEntity<DoctorDto> create(@RequestBody @Valid DoctorDto new_doctor_dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(new_doctor_dto));
    }

    @GetMapping("")
    public ResponseEntity<List<DoctorResponse>> getAll(){
         return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors());
    }
}
