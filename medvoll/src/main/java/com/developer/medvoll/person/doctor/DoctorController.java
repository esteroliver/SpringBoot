package com.developer.medvoll.person.doctor;

import com.developer.medvoll.infra.exceptions.NotFoundException;
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
    public ResponseEntity<Page<DoctorResponse>> getAll(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors(page));
    }

    @GetMapping("{id}")
    public ResponseEntity<DoctorResponse> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctorById(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<DoctorResponse>> getAllAtivo(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctorsByAtivoTrue(page));
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<DoctorResponse>> getAllInativo(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctorsByAtivoFalse(page));
    }

    @PutMapping("")
    @Transactional
    public ResponseEntity<DoctorPutDto> update(@RequestBody @Valid DoctorPutDto update_doctor) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctor(update_doctor));
    }

    @DeleteMapping("/absolute-delete/{id}")
    public ResponseEntity absoluteDelete(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logic-delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws NotFoundException{
        doctorService.logicDeleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

}
