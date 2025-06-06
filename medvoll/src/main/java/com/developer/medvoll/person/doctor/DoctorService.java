package com.developer.medvoll.person.doctor;

import com.developer.medvoll.utils.entities.Address;
import com.developer.medvoll.infra.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    private Doctor getDoctorByIdPrivate(Long id) throws NotFoundException {
        Optional<Doctor> doctor_optional = doctorRepository.findById(id);
        if(doctor_optional.isEmpty()){
            throw new NotFoundException("Doctor not found.");
        }
        return doctor_optional.get();
    }

    public DoctorPostDto createDoctor(DoctorPostDto new_doctor_dto){
        Doctor new_doctor = new Doctor(new_doctor_dto);
        doctorRepository.save(new_doctor);
        return new_doctor_dto;
    }


    public Page<DoctorResponse> getAllDoctors(Pageable page){
        return doctorRepository.findAll(page).map(DoctorResponse::new);
    }

    public DoctorResponse getDoctorById(Long id) throws NotFoundException{
        Doctor doctor = getDoctorByIdPrivate(id);
        return new DoctorResponse(doctor);
    }

    public Page<DoctorResponse> getAllDoctorsByAtivoTrue(Pageable page){
        return doctorRepository.findAllByAtivoTrue(page).map(DoctorResponse::new);
    }

    public Page<DoctorResponse> getAllDoctorsByAtivoFalse(Pageable page){
        return doctorRepository.findAllByAtivoFalse(page).map(DoctorResponse::new);
    }

    public DoctorPutDto updateDoctor(DoctorPutDto doctor_update) throws NotFoundException {
        Doctor doctor = getDoctorByIdPrivate(doctor_update.id());

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

    public void logicDeleteDoctor(Long id) throws NotFoundException{
        Doctor doctor = getDoctorByIdPrivate(id);
        doctor.setAtivo(false);
        doctorRepository.save(doctor);
    }
}