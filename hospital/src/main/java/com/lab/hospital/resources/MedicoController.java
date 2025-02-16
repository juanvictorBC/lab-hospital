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
import org.springframework.web.bind.annotation.RestController;

import com.lab.hospital.entities.Medico;
import com.lab.hospital.services.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping
	public ResponseEntity<Medico> salvarMedico(@RequestBody Medico medico) {
		Medico salvo = medicoService.salvarMedico(medico);
		return ResponseEntity.ok(salvo);
	}

	@GetMapping
	public ResponseEntity<List<Medico>> buscarTodosMedicos() {
		List<Medico> medicos = medicoService.buscarTodosMedicos();
		return ResponseEntity.ok(medicos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable Long id) {
		Optional<Medico> medico = medicoService.buscarMedicoPorId(id);
		return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirMedico(@PathVariable Long id) {
		medicoService.excluirMedico(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Medico> atualizarMedico(@PathVariable Long id, @RequestBody Medico medico) {
		medico = medicoService.atualizarMedico(id, medico);
		return ResponseEntity.ok(medico);
	}

}
