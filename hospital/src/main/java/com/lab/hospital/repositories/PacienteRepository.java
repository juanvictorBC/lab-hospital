package com.lab.hospital.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.hospital.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	List<Paciente> findByNomeContainingIgnoreCase(String nome);

	Optional<Paciente> findByCpf(String cpf);

}
