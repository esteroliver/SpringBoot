package com.developer.medvoll.person.doctor;

import com.developer.medvoll.utils.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public DoctorPostDto createDoctor(DoctorPostDto new_doctor_dto){
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

    public DoctorPutDto updateDoctor(DoctorPutDto doctor_update) throws Exception {
        Optional<Doctor> doctor_optional = doctorRepository.findById(doctor_update.id());
        if(doctor_optional.isEmpty()){
            throw new Exception("Doctor not found.");
        }
        Doctor doctor = doctor_optional.get();

        if( !(doctor_update.nome()).equals(doctor.getNome()) ){
            doctor.setNome(doctor_update.nome());
        }

        Address doctor_update_address = new Address(doctor_update.endereco());

        if( !doctor_update_address.equals(doctor.getEndereco()) ){
            doctor.setEndereco(doctor_update_address);
        }

        doctorRepository.save(doctor);
        return doctor_update;
    }

    public void deleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }
}