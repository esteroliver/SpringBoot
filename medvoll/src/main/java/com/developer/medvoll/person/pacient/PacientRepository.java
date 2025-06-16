package com.developer.medvoll.person.pacient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {
    Optional<Pacient> findByCpf(String cpf);
}
