package com.lab.hospital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.hospital.entities.Internacao;
import com.lab.hospital.entities.enums.StatusInternacao;
import com.lab.hospital.repositories.InternacaoRepository;

@Service
public class InternacaoService {

	@Autowired
	private InternacaoRepository internacaoRepository;

	public Internacao salvarInternacao(Internacao internacao) {
		return internacaoRepository.save(internacao);
	}

	public List<Internacao> buscarTodasInternacoes() {
		return internacaoRepository.findAll();
	}

	public Optional<Internacao> buscarInternacaoPorId(Long id) {
		return internacaoRepository.findById(id);
	}
	
	// Alterar status da internação
    public Internacao alterarStatusInternacao(Long id, StatusInternacao status) {
        Optional<Internacao> internacao = internacaoRepository.findById(id);
        if (internacao.isPresent()) {
            Internacao i = internacao.get();
            i.setStatus(status);
            return internacaoRepository.save(i);
        }
        return null;  // Se a internação não for encontrada
    }

}
