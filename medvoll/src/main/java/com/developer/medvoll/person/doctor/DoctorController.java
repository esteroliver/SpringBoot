package com.developer.medvoll.person.doctor;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class DoctorController {
    @Autowired
    DoctorService doctorService;


    @PostMapping("")
    @Transactional
    public ResponseEntity<DoctorPostDto> create(@RequestBody @Valid DoctorPostDto new_doctor_dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(new_doctor_dto));
    }

    @GetMapping("")
    public ResponseEntity<Page<DoctorResponse>> getByPage(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctorsByPage(page));
    }

    @PutMapping("")
    @Transactional
    public ResponseEntity<DoctorPutDto> update(@RequestBody @Valid DoctorPutDto update_doctor) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctor(update_doctor));
    }
}
