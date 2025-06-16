package com.developer.medvoll.person.pacient;

import com.developer.medvoll.infra.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacientService {

    @Autowired
    PacientRepository pacientRepository;

    public void createPacient(PacientDto pacientDto) throws BadRequestException {
        Pacient newPacient = new Pacient(pacientDto);
        if(pacientRepository.findByCpf(pacientDto.cpf()).isPresent())
            throw new BadRequestException("Pacient already exists.");
        pacientRepository.save(newPacient);
    }
}
