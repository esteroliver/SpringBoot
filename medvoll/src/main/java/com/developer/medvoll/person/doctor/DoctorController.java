package com.developer.medvoll.person.doctor;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class DoctorController {
    @Autowired
    DoctorService doctorService;


    @PostMapping("")
    public ResponseEntity<DoctorDto> create(@RequestBody @Valid DoctorDto new_doctor_dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(new_doctor_dto));
    }

    @GetMapping("/listar-medicos")
    public ResponseEntity<List<DoctorResponse>> getAll(){
         return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors());
    }

    @GetMapping("")
    public ResponseEntity<Page<DoctorResponse>> getByPage(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctorsByPage(page));
    }
}
