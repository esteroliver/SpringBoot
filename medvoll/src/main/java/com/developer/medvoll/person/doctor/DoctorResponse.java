package com.developer.medvoll.person.doctor;

import com.developer.medvoll.utils.enums.SpeciltyEnum;

import com.developer.medvoll.domain.Doctor;

public record DoctorResponse(Long id, String nome, String crm, SpeciltyEnum especialidade) {

	public DoctorResponse(Doctor doctor) {
		this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getEspecialidade());
	}

}
