package com.lab.hospital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.hospital.entities.Paciente;
import com.lab.hospital.repositories.PacienteRepository;
import com.lab.hospital.resources.exceptions.PacienteNaoEncontradoException;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente salvarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());
        if (pacienteExistente.isPresent()) {
            throw new RuntimeException("CPF já cadastrado!"); 
        }
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
    
    

    public Paciente atualizarPaciente(Long id, Paciente paciente) {
        Paciente existente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado!"));
        
        existente.setNome(paciente.getNome());
        existente.setGenero(paciente.getGenero());
        existente.setCpf(paciente.getCpf());
        existente.setDataNascimento(paciente.getDataNascimento());

        return pacienteRepository.save(existente);
    }

    public List<Paciente> buscarPacientesPorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome); 
    }
}
