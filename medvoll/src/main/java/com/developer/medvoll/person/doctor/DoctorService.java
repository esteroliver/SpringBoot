package com.developer.medvoll.person.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public DoctorDto createDoctor(DoctorDto new_doctor_dto){
        Doctor new_doctor = new Doctor(new_doctor_dto);
        doctorRepository.save(new_doctor);
        return new_doctor_dto;
    }

    public List<DoctorResponse> getAllDoctors(){
        return doctorRepository.findAll().stream().map(DoctorResponse::new).toList();
    }

    public Page<DoctorResponse> getDoctorsByPage(Pageable page){
        return doctorRepository.findAll(page).map(DoctorResponse::new);
    }
}