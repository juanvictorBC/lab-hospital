package com.lab.hospital.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab.hospital.entities.Paciente;
import com.lab.hospital.services.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Buscar todos os pacientes ou filtrar por nome
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes(@RequestParam(value = "nome", required = false) String nome) {
        List<Paciente> pacientes;
        if (nome != null && !nome.isEmpty()) {
            pacientes = pacienteService.buscarPacientesPorNome(nome); // Buscar pacientes por nome
        } else {
            pacientes = pacienteService.buscarTodosPacientes(); // Buscar todos os pacientes
        }
        return ResponseEntity.ok().body(pacientes);
    }

    // Buscar paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.buscarPacientePorId(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> salvarPaciente(@RequestBody Paciente paciente) {
        Paciente salvo = pacienteService.salvarPaciente(paciente);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        paciente = pacienteService.atualizarPaciente(id, paciente);
        return ResponseEntity.ok(paciente);
    }
}
