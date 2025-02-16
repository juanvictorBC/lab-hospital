package com.lab.hospital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.hospital.entities.Paciente;
import com.lab.hospital.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	public Paciente salvarPaciente(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	public List<Paciente> buscarTodosPacientes() {
		return pacienteRepository.findAll();
	}

	public Optional<Paciente> buscarPacientePorId(Long id) {
		return pacienteRepository.findById(id);
	}

	public void excluirPaciente(Long id) {
		pacienteRepository.deleteById(id);
	}
}
