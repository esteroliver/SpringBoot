package com.developer.medvoll.person.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByAtivoTrue(Pageable page);
    Page<Doctor> findAllByAtivoFalse(Pageable page);
    Optional<Doctor> findByCpf(String cpf);
    Optional<Doctor> findByCrm(String crm);
}
