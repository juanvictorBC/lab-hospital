package com.lab.hospital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.hospital.entities.Leito;
import com.lab.hospital.repositories.LeitoRepository;

@Service
public class LeitoService {

	@Autowired
	private LeitoRepository leitoRepository;

	public Leito salvarLeito(Leito leito) {
		return leitoRepository.save(leito);
	}

	public List<Leito> buscarTodosLeitos() {
		return leitoRepository.findAll();
	}

	public Optional<Leito> buscarLeitoPorId(Long id) {
		return leitoRepository.findById(id);
	}

	public void excluirLeito(Long id) {
		leitoRepository.deleteById(id);
	}
}
