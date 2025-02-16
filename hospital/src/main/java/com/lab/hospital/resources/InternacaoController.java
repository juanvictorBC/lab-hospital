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

import com.lab.hospital.entities.Internacao;
import com.lab.hospital.entities.enums.StatusInternacao;
import com.lab.hospital.services.InternacaoService;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {

	@Autowired
	private InternacaoService internacaoService;

	@PostMapping
	public ResponseEntity<Internacao> criarInternacao(@RequestBody Internacao internacao) {
	    System.out.println("Recebendo Internacao: " + internacao);
	    if (internacao.getLeito() == null || internacao.getLeito().getId() == null) {
	        System.out.println("Erro: Leito está nulo!");
	        return ResponseEntity.badRequest().body(null);
	    }

	    Internacao novaInternacao = internacaoService.salvarInternacao(internacao);
	    return ResponseEntity.ok(novaInternacao);
	}
	
	@GetMapping
	public ResponseEntity<List<Internacao>> buscarTodasInternacoes() {
		List<Internacao> internacoes = internacaoService.buscarTodasInternacoes();
		return ResponseEntity.ok(internacoes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Internacao> buscarInternacaoPorId(@PathVariable Long id) {
		Optional<Internacao> internacao = internacaoService.buscarInternacaoPorId(id);
		return internacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirInternacao(@PathVariable Long id) {
		try {
			internacaoService.excluirInternacao(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(null);
		}
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Internacao> alterarStatusInternacao(@PathVariable Long id,
			@RequestBody StatusInternacao status) {
		Internacao internacao = internacaoService.alterarStatusInternacao(id, status);
		return internacao != null ? ResponseEntity.ok(internacao) : ResponseEntity.notFound().build();
	}
}
