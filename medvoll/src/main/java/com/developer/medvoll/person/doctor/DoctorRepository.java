package com.developer.medvoll.person.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.developer.medvoll.domain.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Page<Doctor> findAllByAtivoTrue(Pageable page);

	Page<Doctor> findAllByAtivoFalse(Pageable page);

}
