package com.lab.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.hospital.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
