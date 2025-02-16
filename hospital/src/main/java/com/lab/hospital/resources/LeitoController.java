package com.lab.hospital.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.hospital.entities.Leito;
import com.lab.hospital.services.LeitoService;

@RestController
@RequestMapping("/leitos")
public class LeitoController {

	@Autowired
	private LeitoService leitoService;

	@GetMapping
	public ResponseEntity<List<Leito>> buscarTodosLeitos() {
		List<Leito> leitos = leitoService.buscarTodosLeitos();
		return ResponseEntity.ok(leitos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Leito> buscarLeitoPorId(@PathVariable Long id) {
		Optional<Leito> leito = leitoService.buscarLeitoPorId(id);
		return leito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLeito(@PathVariable Long id) {
        leitoService.excluirLeito(id);
        return ResponseEntity.noContent().build();
	}

}
