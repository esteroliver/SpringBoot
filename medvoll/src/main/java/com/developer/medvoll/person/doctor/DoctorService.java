package com.developer.medvoll.person.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public Doctor createDoctor(Doctor new_doctor){
        doctorRepository.save(new_doctor);
        return new_doctor;
    }
}
