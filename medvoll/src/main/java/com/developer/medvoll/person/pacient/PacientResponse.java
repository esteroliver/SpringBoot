package com.developer.medvoll.person.pacient;

import com.developer.medvoll.utils.enums.GenderEnum;

import java.util.Date;
import java.util.List;

public record PacientResponse(Long id, String nome, Date dataNascimento, GenderEnum genero, String telefone, String email, List<String> cid, List<String> alergia) {

    public PacientResponse(Pacient pacient){
        this(pacient.getId(), pacient.getNome(), pacient.getDataNascimento(), pacient.getGenero(), pacient.getTelefone(), pacient.getEmail(), pacient.getCid(), pacient.getAlergia());
    }

}
