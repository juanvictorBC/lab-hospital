package com.lab.hospital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.hospital.entities.Medico;
import com.lab.hospital.repositories.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	public Medico salvarMedico(Medico medico) {
		return medicoRepository.save(medico);
	}

	public List<Medico> buscarTodosMedicos() {
		return medicoRepository.findAll();
	}

	public Optional<Medico> buscarMedicoPorId(Long id) {
		return medicoRepository.findById(id);
	}

	public void excluirMedico(Long id) {
		medicoRepository.deleteById(id);
	}
}
