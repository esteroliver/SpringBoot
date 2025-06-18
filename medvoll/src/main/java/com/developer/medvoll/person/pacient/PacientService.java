package com.developer.medvoll.person.pacient;

import com.developer.medvoll.infra.exceptions.BadRequestException;
import com.developer.medvoll.infra.exceptions.NotFoundException;
import com.developer.medvoll.utils.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PacientService {

    @Autowired
    PacientRepository pacientRepository;

    public PacientDto createPacient(PacientDto pacientDto) throws BadRequestException {
        Pacient newPacient = new Pacient(pacientDto);
        if(pacientRepository.findByCpf(pacientDto.cpf()).isPresent())
            throw new BadRequestException("Pacient already exists.");
        pacientRepository.save(newPacient);
        return pacientDto;
    }

    public PacientResponse getPacientByCpf(String cpf) throws NotFoundException {
        Optional<Pacient> pacient = pacientRepository.findByCpf(cpf);
        if(pacient.isEmpty())
            throw new NotFoundException("Pacient not found.");
        return new PacientResponse(pacient.get());
    }

    public Page<PacientResponse> getAllPacients(Pageable page){
        return pacientRepository.findAll(page).map(PacientResponse::new);
    }

    public PacientResponse updatePacient(PacientPutDto pacientUpdate) throws NotFoundException{
        Optional<Pacient> pacientOpt = pacientRepository.findById(pacientUpdate.id());
        if(pacientOpt.isEmpty())
            throw new NotFoundException("Pacient not found.");
        Pacient pacient = pacientOpt.get();

        if(!Objects.isNull(pacientUpdate.endereco()))
            pacient.setEndereco(new Address(pacientUpdate.endereco()));

        if(!Objects.isNull(pacientUpdate.genero()))
            pacient.setGenero(pacientUpdate.genero());

        if(!Objects.isNull(pacientUpdate.telefone()))
            pacient.setTelefone(pacientUpdate.telefone());

        if(!Objects.isNull(pacientUpdate.email()))
            pacient.setEmail(pacientUpdate.email());

        if(!Objects.isNull(pacientUpdate.cid()))
            pacient.setCid(pacientUpdate.cid());

        if(!Objects.isNull(pacientUpdate.alergia()))
            pacient.setAlergia(pacientUpdate.alergia());

        pacientRepository.save(pacient);

        return new PacientResponse(pacient);
    }
}
